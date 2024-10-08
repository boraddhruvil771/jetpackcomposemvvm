package com.example.jetpackcomposebase.ui.signup.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jetpackcomposebase.R
import com.example.jetpackcomposebase.base.ToolBarData
import com.example.jetpackcomposebase.navigation.Destination
import com.example.jetpackcomposebase.navigation.NAV_HOME
import com.example.jetpackcomposebase.navigation.NAV_LOGIN
import com.example.jetpackcomposebase.navigation.NAV_PRIVACY_POLICY
import com.example.jetpackcomposebase.navigation.navigateTo
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.login.model.LoginResponseModel
import com.example.jetpackcomposebase.ui.signup.viewmodel.SignupViewmodel
import com.example.jetpackcomposebase.utils.DebugLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignupScreenView(
    navController: NavController,
    drawerState: DrawerState,
    bottomBarVisibility: (Boolean) -> Unit,
    topBar: (ToolBarData) -> Unit,
    circularProgress: (Boolean) -> Unit,
    signupViewModel: SignupViewmodel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        topBar(
            ToolBarData(
                title = "Login",
                isVisible = false,
                isDrawerIcon = false,
                isBackIconVisible = true,
            )
        )
        bottomBarVisibility(false)
        circularProgress(false)
        /* observeData(
             viewModel = signupViewModel,
             context = context,
             navController = navController,
             circularProgress = { circularProgress(it) })*/
        delay(2500)

    }
    SignUpUI(navController = navController, signupViewModel = signupViewModel, circularProgress)
    BackHandler {
        navController?.popBackStack()
    }
}

@Composable
fun SignUpUI(
    navController: NavController,
    signupViewModel: SignupViewmodel,
    circularProgress: (Boolean) -> Unit
) {

    val context = LocalContext.current
    val responseApi by signupViewModel.signupResponse.collectAsState()


    when (responseApi) {
        is ResponseHandler.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is ResponseHandler.OnError -> {
            Log.d("TAG", "Signup:${(responseApi as ResponseHandler.OnError).message}")
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

                navController.navigate(NAV_HOME)

                navigateTo(
                    navHostController = navController,
                    route = Destination.HomeScreen.fullRoute,
                    popUpToRoute = Destination.LoginScreen.fullRoute,
                    isInclusive = true
                )
            } else {
                signupView(navController = navController, signupViewModel = signupViewModel)
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
            signupView(navController = navController, signupViewModel = signupViewModel)
        }
    }
}

@Composable
fun signupView(navController: NavController, signupViewModel: SignupViewmodel) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // State variables for holding input values
        var ssn by remember { mutableStateOf("") }
        var mobileNumber by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }


        var isssnValid by remember { mutableStateOf(true) }
        var isMobileNumberValid by remember { mutableStateOf(true) }
        var isPasswordValid by remember { mutableStateOf(true) }


        val mobileNumberMaxLength = 11 // Set your desired max length for mobile number
        val passwordMaxLength = 16 // Set your desired max length for password


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF)), // Background color matching the design
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            /* Text(
                 text = "Encore Enterprises",
                 color = Color.Blue,
                 fontSize = 30.sp,
                 modifier = Modifier.padding(bottom = 40.dp)
             )*/

            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24), // replace with your logo
                contentDescription = "back Logo",
                modifier = Modifier
                    .height(25.dp)
                    .padding(bottom = 40.dp),
                alignment = Alignment.TopStart,
            )


            Image(
                painter = painterResource(id = R.drawable.mainlogo), // replace with your logo
                contentDescription = "Logo",
                modifier = Modifier
                    .height(75.dp)
                    .padding(bottom = 40.dp),
                contentScale = ContentScale.Fit
            )

            // Input fields
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = ssn,
                    onValueChange = { ssn = it },
                    label = { Text("SSN") },
                    isError = !isssnValid,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = mobileNumber,
                    onValueChange = {
                        if (it.length <= mobileNumberMaxLength) { // Check length before updating
                            mobileNumber = it
                        }
                    }, isError = !isMobileNumberValid,
                    label = { Text("Mobile Number") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        if (it.length <= passwordMaxLength) { // Check length before updating
                            password = it
                        }
                    }, label = { Text("Password") },
                    isError = !isPasswordValid,
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    trailingIcon = {
                        TextButton(onClick = {
                        }) {
                            Text("Show")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        isssnValid = ssn.isNotEmpty()
                        isMobileNumberValid = mobileNumber.isNotEmpty()
                        isPasswordValid = password.isNotEmpty()

                        if (isssnValid && isMobileNumberValid && isPasswordValid) {
                            signupViewModel.callSignup(ssn, mobileNumber, password)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = ssn.isNotEmpty() && mobileNumber.isNotEmpty() && password.isNotEmpty()
                ) {
                    Text("Submit")
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "By Creating an Account, You Agree with our",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 12.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                TextButton(onClick = {
                    navController.navigate(NAV_PRIVACY_POLICY)
                }) {
                    Text(
                        text = "Privacy Policy",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "&", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = {
                    navController.navigate(NAV_PRIVACY_POLICY)
                }) {
                    Text(
                        text = "Term and Condition",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

fun observeData(
    viewModel: SignupViewmodel,
    context: Context,
    navController: NavController,
    circularProgress: (Boolean) -> Unit
) {

    viewModel.viewModelScope.launch {
        viewModel.signupResponse.collect {
            when (it) {
                is ResponseHandler.Loading -> {
                    circularProgress(true)
                }

                is ResponseHandler.OnFailed -> {
                    circularProgress(false)
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<LoginResponseModel>?> -> {
                    when {
                        it.response?.data?.userEmail?.isNotEmpty() == true -> {
                            DebugLog.e("response : email:${it.response.data?.userEmail}")
                            Toast.makeText(
                                context,
                                "Email:${it.response.data?.userEmail}",
                                Toast.LENGTH_SHORT
                            ).show()
                            navController.navigate(NAV_LOGIN)

                            navigateTo(
                                navHostController = navController,
                                route = Destination.LoginScreen.fullRoute,
                                popUpToRoute = Destination.LoginScreen.fullRoute,
                                isInclusive = true
                            )
                        }

                        else -> {
                        }
                    }
                }

                else -> {
                    circularProgress(false)
                }
            }
        }
    }
}

