package com.example.jetpackcomposebase.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.jetpackcomposebase.ui.dashboard.viewmodel.HomeViewModel

class CommonUtils {
    companion object {

        //Preference name
        private const val prefName = "encorePref"
        private const val prefNameToken = "encorePrefToken"

        fun setData(context: Context, key: String, data: String) {
            val editor = preferenceManager(context).edit()
            editor.putString(key, data)
            editor.apply()
        }

        fun getData(context: Context, key: String): String? {
            return preferenceManager(context).getString(key, "")
        }

        //Keys
        var IS_LOGIN = "is_login"
        var IS_LOGGED_IN = false
        var IS_NOTIFICATION = false
        var IS_LOGGED_IN_OTP = false
        var tokenOTP = ""
        var userSSN = "userSSN"
        var tier = "tier"
        var getHomeViewModel: HomeViewModel? = null
        var LANGUAGE = "language"
        var FCM_TOKEN = "fcm_token"
        var NEW_FCM_TOKEN = "new_fcm_token"
        var AUTH_TOKEN = "auth_token"
        var AUTH_TOKEN_OTP = "auth_token_otp"
        const val mobile = "mobile"
        const val update = "update"
        const val forgot = "forgot"
        const val signup = "signup"
        const val LOGIN = "login"
        const val SPOUSE_WELCOME_MESSAGE = "SPOUSE_WELCOME_MESSAGE"
        const val HOME = "home"
        const val screen = "screen"
        const val OTP = "otp"
        const val USER_SNN = "snn"
        const val HAS_MEDICAL_PLAN = "hasMedicalPlan"
        const val HAS_DENTAL_PLAN = "hasDentalPlan"
        const val HAS_VISION_PLAN = "hasVisionPlan"
        const val USER_PARENT_TIEAR = "userParentTiear"
        const val YEAR = "year"
        const val EFFECTIVE_DATE = "effectiveDate"
        const val UMR_URL = "umr_url"
        const val USER_ID = "userId"
        const val USER_NAME = "userName"
        const val FACILITY_ID = "facilityId"
        const val USER_TYPE = "userType"
        const val BASE_URL = "basUrl"
        const val PASSWORD = "password"
        const val list = "list"
        const val USER_RELATION = "USER_RELATION"

        fun preferenceManager(context: Context): SharedPreferences {
            return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        }

        private fun preferenceTokenManager(context: Context): SharedPreferences {
            return context.getSharedPreferences(prefNameToken, Context.MODE_PRIVATE)
        }


    }
}
