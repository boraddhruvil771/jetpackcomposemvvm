package com.example.jetpackcomposebase.ui.splash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetpackcomposebase.R
import com.example.jetpackcomposebase.base.ToolBarData
import com.example.jetpackcomposebase.navigation.NAV_LOGIN
import com.example.jetpackcomposebase.ui.theme.MainComposeTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreenView(
    navController: NavController?,
    bottomBarVisibility: (Boolean) -> Unit,
    topBar: (ToolBarData) -> Unit,
) {
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
            navController?.popBackStack()
            navController?.navigate(NAV_LOGIN)
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