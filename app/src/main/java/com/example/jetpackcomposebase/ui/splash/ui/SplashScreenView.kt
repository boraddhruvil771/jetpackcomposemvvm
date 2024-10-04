package com.example.jetpackcomposebase.ui.splash.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcomposebase.R
import com.example.jetpackcomposebase.base.ToolBarData
import com.example.jetpackcomposebase.navigation.NAV_HOME
import com.example.jetpackcomposebase.navigation.NAV_LOGIN
import com.example.jetpackcomposebase.ui.theme.MainComposeTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreenView(
    navController: NavController?,
    bottomBarVisibility: (Boolean) -> Unit,
    topBar: (ToolBarData) -> Unit,
) {

    // Get context to access SharedPreferences
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    MainComposeTheme {
        LaunchedEffect(key1 = true) {
            topBar(
                ToolBarData(
                    title = "Splash",
                    isVisible = false,
                    isDrawerIcon = false,
                    isBackIconVisible = false,
                    isTitleVisible = false
                )
            )
            bottomBarVisibility(false)
            delay(2500)
            // Check if the user is logged in from SharedPreferences
            val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

            if (isLoggedIn) {
                // User is logged in, navigate to Home screen
                if (navController != null) {
                    navController.popBackStack()
                } // Clear splash from backstack
                if (navController != null) {
                    navController.navigate(NAV_HOME)
                } // Navigate to home
            } else {
                // User is not logged in, navigate to Login screen
                if (navController != null) {
                    navController.popBackStack()
                } // Clear splash from backstack
                if (navController != null) {
                    navController.navigate(NAV_LOGIN)
                } // Navigate to login
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .size(48.dp)
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.mainlogo),
                contentDescription = "splash"
            )
        }
    }
}