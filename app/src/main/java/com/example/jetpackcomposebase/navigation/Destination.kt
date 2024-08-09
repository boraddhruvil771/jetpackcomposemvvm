package com.example.jetpackcomposebase.navigation

sealed class Destination(protected val route: String, vararg params: String) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    data object SplashScreen : NoArgumentsDestination(NAV_SPLASH)
    data object LoginScreen : NoArgumentsDestination(NAV_LOGIN)

    data object HomeScreen : NoArgumentsDestination(NAV_HOME)
    data object SettingsScreen : NoArgumentsDestination(NAV_SETTINGS)
    data object ProfileScreen : NoArgumentsDestination(NAV_PROFILE)
    data object signupScreen : NoArgumentsDestination(NAV_SIGNUP)
    data object privacyPolicyScreen : NoArgumentsDestination(NAV_PRIVACY_POLICY)



}

internal fun String.appendNavParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}
