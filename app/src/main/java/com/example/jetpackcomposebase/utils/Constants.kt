package com.example.jetpackcomposebase.utils

import androidx.datastore.preferences.core.stringPreferencesKey
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object Constants {
    val JSON = jacksonObjectMapper()
    var KEY_BUNDLE = "bundle"

    var KEY_GRAPH_ID = "graphId"
    var KEY_START_DESTINATION = "startDestination"
    const val USER_DATA = "login_data"

    //Keys
    const val IS_LOGIN = "is_login"
    const val AUTHORIZATION = "Authorization"
    const val BEARER = "Bearer"
    const val LOGIN_MODEL = "login_model"
    const val TIER = "tier"
    const val AUTH_TOKEN = "auth_token"
    const val SOMETHING_WENT_WRONG = "Something went wrong"
    const val MOBILE = "mobile"
    const val SPLASH = "splash"
    const val SIGN_UP = "signup"


    //Interceptors Keys
    const val KEY_CONTENT_TYPE = "Content-Type"
    const val KEY_ACCEPT_LANGUAGE = "Accept-Language"
    const val IS_MOBILE = "is-mobile"
    const val DEVICE_TYPE = "deviceType"
    const val LANGUAGE_CODE = "lang-code"
    const val VERSION = "version"
    const val APPLICATION_JSON = "application/json"
    const val IS_MOBILE_1 = "1"
    const val ANDROID = "android"
    const val EN = "en"
    const val ACCEPT_CAPITAL = "Accept"
    const val REJECT_CAPITAL = "Reject"
    const val ES = "es"
    const val IN_NETWORK_DOCTER_SCREEN = "In Network Doctor Screen"

    var DSN =
        "https://e68671b99bf2afd717617c18a3aa1dc4@o4507655094140928.ingest.us.sentry.io/4507666947309568"
    var PRIVATE_POLICY = "https://www.brainvire.com/";

    object DataStore {
        val SECURED_DATA = stringPreferencesKey("secured_data")
    }


    object ToolbarData {
        const val Unsubscribe: String = "Unsubscribe"

    }

}