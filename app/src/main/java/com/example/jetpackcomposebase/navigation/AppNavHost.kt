package com.example.jetpackcomposebase.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcomposebase.base.LocaleManager
import com.example.jetpackcomposebase.base.ToolBarData
import com.example.jetpackcomposebase.ui.dashboard.ui.HomeScreenView
import com.example.jetpackcomposebase.ui.dashboard.viewmodel.HomeViewModel
import com.example.jetpackcomposebase.ui.login.ui.LoginScreenView
import com.example.jetpackcomposebase.ui.login.viewmodel.LoginViewModel
import com.example.jetpackcomposebase.ui.privcacypolicy.ui.PrivacyPolicyUI
import com.example.jetpackcomposebase.ui.privcacypolicy.viewmodel.PrivacyPolicyViewmodel
import com.example.jetpackcomposebase.ui.profile.ui.ProfileScreenView
import com.example.jetpackcomposebase.ui.settings.ui.SettingScreenView
import com.example.jetpackcomposebase.ui.settings.viewmodel.SettingViewModel
import com.example.jetpackcomposebase.ui.signup.ui.SignupScreenView
import com.example.jetpackcomposebase.ui.signup.viewmodel.SignupViewmodel
import com.example.jetpackcomposebase.ui.splash.ui.SplashScreenView
import com.example.jetpackcomposebase.ui.splash.viewmodel.SplashViewModel


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination = Destination.LoginScreen,
    modifier: Modifier,
    topBar: (ToolBarData) -> Unit,
    bottomBarVisibility: (Boolean) -> Unit,
    circularProgress: (Boolean) -> Unit,
    localeManager: LocaleManager
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    // Inject LocaleManager using Hilt

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.fullRoute
    ) {
        composable(Destination.SplashScreen.fullRoute) {
            SplashScreenView(
                navController,
                topBar = topBar,
                bottomBarVisibility = { bottomBarVisibility(it) },
            )
        }

        composable(Destination.LoginScreen.fullRoute) {
            val viewModel = hiltViewModel<LoginViewModel>()

            LoginScreenView(
                navController = navController,
                loginViewModel = viewModel,
                topBar = topBar,
                bottomBarVisibility = { bottomBarVisibility(it) },
                circularProgress = circularProgress
            )

        }

        composable(Destination.LoginScreen.fullRoute) {
            val viewModel = hiltViewModel<LoginViewModel>()

            LoginScreenView(
                navController = navController,
                loginViewModel = viewModel,
                topBar = topBar,
                bottomBarVisibility = { bottomBarVisibility(it) },
                circularProgress = circularProgress
            )

        }

        composable(Destination.HomeScreen.fullRoute) {
            val viewModel = hiltViewModel<HomeViewModel>()

            HomeScreenView(
                navController = navController,
                drawerState = drawerState,
                topBar = topBar,
                viewModel = viewModel,
                bottomBarVisibility = { bottomBarVisibility(it) },
                circularProgress = circularProgress
            )
        }

        composable(Destination.SettingsScreen.fullRoute) {
            val viewModel = hiltViewModel<SettingViewModel>()
            SettingScreenView(
                navController = navController,
                drawerState = drawerState,
                topBar = topBar,
                bottomBarVisibility = { bottomBarVisibility(it) },
                circularProgress = circularProgress,
                settingViewModel = viewModel
            )
        }

        composable(Destination.ProfileScreen.fullRoute) {
            val viewModel = hiltViewModel<SplashViewModel>()
            ProfileScreenView(
                navController = navController,
                drawerState = drawerState,
                topBar = topBar,
                bottomBarVisibility = { bottomBarVisibility(it) },
                circularProgress = circularProgress
            )
        }
        composable(Destination.signupScreen.fullRoute) {
            val viewModel = hiltViewModel<SignupViewmodel>()
            SignupScreenView(
                navController = navController,
                drawerState = drawerState,
                topBar = topBar,
                bottomBarVisibility = { bottomBarVisibility(it) },
                circularProgress = circularProgress
            )
        }
        composable(Destination.privacyPolicyScreen.fullRoute) {
            val viewModel = hiltViewModel<PrivacyPolicyViewmodel>()
            PrivacyPolicyUI(
                navController = navController,
                drawerState = drawerState,
                topBar = topBar,
                bottomBarVisibility = { bottomBarVisibility(it) },
                circularProgress = circularProgress
            )
        }

    }


}


fun navigateTo(
    navHostController: NavController,
    route: String,
    popUpToRoute: String = "",
    isInclusive: Boolean = false,
    isSingleTopNav: Boolean = false,
) {
    navHostController.navigate(route) {
        if (popUpToRoute.isNotEmpty()) {
            popUpTo(popUpToRoute) {
                inclusive = isInclusive
            }
        }
        launchSingleTop = isSingleTopNav
    }
}