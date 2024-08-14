package com.example.jetpackcomposebase.ui.login.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetpackcomposebase.R
import com.example.jetpackcomposebase.base.ToolBarData
import com.example.jetpackcomposebase.navigation.Destination
import com.example.jetpackcomposebase.navigation.NAV_HOME
import com.example.jetpackcomposebase.navigation.NAV_PRIVACY_POLICY
import com.example.jetpackcomposebase.navigation.NAV_SIGNUP
import com.example.jetpackcomposebase.navigation.navigateTo
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.login.model.LoginResponseModel
import com.example.jetpackcomposebase.ui.login.viewmodel.LoginViewModel
import com.example.jetpackcomposebase.utils.CommonUtils
import com.example.jetpackcomposebase.utils.Constants
import com.example.jetpackcomposebase.utils.MyPreference

@Composable
fun LoginScreenView(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    bottomBarVisibility: (Boolean) -> Unit,
    topBar: (ToolBarData) -> Unit,
    circularProgress: (Boolean) -> Unit,
) {


    LaunchedEffect(Unit) {
        topBar(
            ToolBarData(
                title = "Login", isVisible = false, isDrawerIcon = false, isBackIconVisible = false
            )
        )
        bottomBarVisibility(false)
        circularProgress(false)

    }
    LoginUI(navController = navController, loginViewModel = loginViewModel, circularProgress)
}


@Composable
fun LoginUI(
    navController: NavController,
    loginViewModel: LoginViewModel,
    circularProgress: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val responseApi by loginViewModel.loginResponse.collectAsState()
    val mPref = MyPreference(context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE))  // Initialize mPref here

    when (responseApi) {
        is ResponseHandler.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is ResponseHandler.OnError -> {
            Log.d("TAG", "LoginUI:${(responseApi as ResponseHandler.OnError).message}")
            Text(
                text = "Error: ${(responseApi as ResponseHandler.OnError).message}",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center),
                color = Color.Blue
            )
        }

        is ResponseHandler.OnSuccessResponse -> {

            Log.d(
                "TAG",
                "LoginUI: on success${(responseApi as ResponseHandler.OnSuccessResponse).response?.status}"
            )
            if ((responseApi as ResponseHandler.OnSuccessResponse).response?.status_code == 200) {
                CommonUtils.IS_LOGIN = true.toString()

                CommonUtils.setData(
                    context,
                    CommonUtils.tier,
                    (responseApi as ResponseHandler.OnSuccessResponse<ResponseData<LoginResponseModel>?>).response?.data?.userTiear.toString()
                )

                CommonUtils.setData(
                    context,
                    CommonUtils.HAS_MEDICAL_PLAN,
                    (responseApi as ResponseHandler.OnSuccessResponse<ResponseData<LoginResponseModel>?>).response?.data?.hasMedicalPlan.toString()
                )

                CommonUtils.setData(
                    context,
                    CommonUtils.USER_PARENT_TIEAR,
                    (responseApi as ResponseHandler.OnSuccessResponse<ResponseData<LoginResponseModel>?>).response?.data?.userParentTiear.toString()
                )

                (responseApi as ResponseHandler.OnSuccessResponse<ResponseData<LoginResponseModel>?>).response?.data?.accessToken?.let { it1 ->
                    mPref.setValueString(
                        Constants.AUTH_TOKEN,
                        it1
                    )
                }
                (responseApi as ResponseHandler.OnSuccessResponse<ResponseData<LoginResponseModel>?>).response?.data?.userTiear?.let { it1 ->
                    mPref.setValueInt(
                        Constants.TIER,
                        it1
                    )
                }
                CommonUtils.updateFCMTokenInPreference(context)

                CommonUtils.setData(
                    context,
                    CommonUtils.IS_LOGIN,
                    "true"
                )

                mPref.setValueBoolean(Constants.IS_LOGIN, true)
                mPref.setBeanValue(
                    Constants.LOGIN_MODEL,
                    (responseApi as ResponseHandler.OnSuccessResponse<ResponseData<LoginResponseModel>?>).response?.data!!
                )
                navController.navigate(NAV_HOME)

                CommonUtils.setData(
                    context,
                    CommonUtils.USER_SNN,
                    (responseApi as ResponseHandler.OnSuccessResponse<ResponseData<LoginResponseModel>?>).response?.data?.userSSN.toString()
                )
                CommonUtils.IS_LOGGED_IN_OTP = false
                CommonUtils.IS_LOGGED_IN = true

                navController.navigate(NAV_HOME)

                navigateTo(
                    navHostController = navController,
                    route = Destination.HomeScreen.fullRoute,
                    popUpToRoute = Destination.LoginScreen.fullRoute,
                    isInclusive = true
                )
            } else {
                LoginView(navController = navController)
                Toast.makeText(
                    context,
                    "${(responseApi as ResponseHandler.OnSuccessResponse).response?.message} ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        is ResponseHandler.OnFailed -> {
            Log.d("TAG", "LoginUI: failed ${(responseApi as ResponseHandler.OnFailed).message}")
            Text(
                text = "Error: ${(responseApi as ResponseHandler.OnFailed).message}",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        else -> {
            LoginView(navController = navController)
        }
    }
}


@Composable
fun LoginView(navController: NavController, loginViewModel: LoginViewModel = hiltViewModel()) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        var mobileNumber by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        var isMobileNumberValid by remember { mutableStateOf(true) }
        var isPasswordValid by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.mainlogo), // replace with your logo
                contentDescription = "Logo",
                modifier = Modifier
                    .height(75.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Login",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = mobileNumber,
                onValueChange = { mobileNumber = it },
                label = { Text("Mobile Number") },
                isError = !isMobileNumberValid,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                isError = !isPasswordValid, // Set error state
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = "Toggle password visibility")
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Text(text = "Forgot Password?",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp)
                    .clickable { /* handle click */ })

            Button(
                onClick = {
                    isMobileNumberValid = mobileNumber.isNotEmpty()
                    isPasswordValid = password.isNotEmpty()
                    if (isMobileNumberValid && isPasswordValid) {
                        loginViewModel.callLoginAPI(mobileNumber, password)
                    }
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Don't Have an Account? ", modifier = Modifier.padding(bottom = 4.dp)
            )
            ClickableText(
                text = AnnotatedString("Sign up"),
                onClick = {
                    navController.navigate(NAV_SIGNUP)
                },
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                ClickableText(
                    text = AnnotatedString("Privacy Policy"),
                    onClick = {
                        navController.navigate(NAV_PRIVACY_POLICY)
                    },
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
                )
                Text(
                    text = " & ",
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
                )
                ClickableText(
                    text = AnnotatedString("Term and Condition"),
                    onClick = {
                        navController.navigate(NAV_PRIVACY_POLICY)
                    },
                    style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

