package com.example.jetpackcomposebase.utils

import androidx.datastore.preferences.core.stringPreferencesKey
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object Constants {
    val JSON = jacksonObjectMapper()
    var KEY_BUNDLE = "bundle"

    var KEY_GRAPH_ID = "graphId"
    var KEY_START_DESTINATION = "startDestination"


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
    const val ES = "es"
    const val ACCEPT_CAPITAL = "accept-capital"

    var DSN =
        "https://e68671b99bf2afd717617c18a3aa1dc4@o4507655094140928.ingest.us.sentry.io/4507666947309568"
    var PRIVATE_POLICY = "https://www.brainvire.com/";

    object DataStore {
        val SECURED_DATA = stringPreferencesKey("secured_data")
    }


    object ToolbarData {
        const val Unsubscribe: String = "Unsubscribe"

    }

    const val EMPLOYEE = "employee"

    const val PASSWORD_PATTERN =
        "(^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#^])[A-Za-z\\d@\$!%*?&#^]{6,}\$)"
    const val PACKAGE = "package"
    const val DIRECT_PRIMARY_AVAILABLE_SERVICES_LIST = "DirectPrimaryAvailableServices"
    const val PEDIATRIC_AVAILABLE_SERVICES_LIST = "PediatricAvailableServices"
    const val DIRECT_PRIMARY_CONTACT_NUMBER = "DirectPrimaryContactNumber"
    const val PEDIATRIC_CONTACT_NUMBER = "PediatricPrimaryContactNumber"
    const val USER_DATA = "login_data"

    const val MESSAGE_TYPE_PARAGRAPH = "paragraph"
    const val MESSAGE_TYPE_BULLET = "bullet"
    const val MESSAGE_ALIGNMENT_LEFT = "left"

    const val SOMETHING_WENT_WRONG = "Something went wrong"

    //Interceptors Keys
    const val IN_NETWORK_DOCTER_SCREEN = "Find Your in-Network Doctor"

    //Bundle Keys
    const val CLICK_ACTION = "click_action"
    const val MESSAGE = "Message"
    const val ACCEPT_REJECT = "AcceptOrReject"
    const val NOTIFICATION_LIST = "NotificationList"
    const val TITLE = "title"
    const val BODY = "body"
    const val CHANNEL_ID = "CHANNEL_ID"
    const val PAGE_TITLE = "pageTitle"
    const val PRIVACY_POLICY = "PRIVACY_POLICY"
    const val TERM_CONDITION_LABEL = "Terms of Use"
    const val PRIVACY_POLICY_LABEL = "Privacy Policy"

    //Notification keys
    const val NOTIFICATION_USER_SSN = "notificationUserSSN"
    const val ACCEPT = "accept"
    const val REJECT = "reject"
    const val REJECT_CAPITAL = "Reject"
    const val PENDING = "pending"
    const val NOTIFICATION_TITLE = "notificationTitle"
    const val NOTIFICATION_BODY = "notificationBody"
    const val IS_NOTIFICATION_DISPLAY = "isNotificationDisplay"

    //Keys
    const val IS_LOGIN = "is_login"
    const val AUTHORIZATION = "Authorization"
    const val BEARER = "Bearer"
    const val LOGIN_MODEL = "login_model"
    const val TIER = "tier"
    const val AUTH_TOKEN = "auth_token"
    const val MOBILE = "mobile"
    const val SPLASH = "splash"
    const val SIGN_UP = "signup"
    const val IS_ACCEPTED = "isAccepted"
    const val CHILD = "child"

    const val HOME = "home"
    const val SCREEN = "screen"
    const val POLICY_MOBILE = "policy_mobile"
    const val TERM_CONDITION = "term_condition"
    const val ID_CARD = "id_card"
    const val USER_SNN = "snn"
    const val VERSION_LABEL = "Version"

    const val APP_PLAY_STORE_LINK =
        "https://play.google.com/store/apps/details?id=com.app.encorehealthcare"
    const val FAQ_URL = "https://encorehr.zendesk.com/hc/en-us/requests/new"
    const val CHOOSE_PLAN_A_URL =
        "https://www.umr.com/"

    const val DEMO_USER_SSN = "000220000"
//    const val DEMO_USER_SSN = "883883903"

    //Keys for passing data into Bundle
    const val KEY_USERID = "userID"
    const val FAMILY_MEMBER_NAME = "familyMemberName"
    const val USER_RELATION = "userRelation"
    const val USER_TIER = "userTier"
    const val HAS_MEDICAL_PLAN = "hasMedicalPlan"
    const val HAS_DENTAL_PLAN = "hasDentalPlan"
    const val HAS_VISION_PLAN = "hasVisionPlan"
    const val EFFECTIVE_DATE = "effectiveDate"
    const val YEAR = "year"
    const val DOCUMENTS = "documents"
    const val DOCUMENTS_URL = "documentsUrl"
    const val DOCUMENTS_PDF = "pdf"
    const val PRE_ENROLL_PLAN_TYPE = "explorePlanType"
    const val PRE_ENROLL_USER = "Pre-Enrolled"
    const val ENROLL_USER = "enrolled"
    const val USER_TYPE = "userType"
    const val PLAN_A = "UHC EPO Plan"
    const val PLAN_B = "UHC PPO Plan"
    const val USER_NAME = "userName"
    const val INTERNAL_KEY = "internal"
    const val EXTERNAL_KEY = "external"
    const val PLAN_TYPE = "planType"
    const val PLAN_TYPE_MEDICAL = "Medical"
    const val PLAN_TYPE_VISION = "Vision"
    const val PLAN_TYPE_DENTAL = "Dental"


    // key for firebase message subscribe
    const val ALL_USER = "all_users"
    const val ALL_EMPLOYEES = "all_employees"
    const val ALL_KEY = "All"
    const val ALL_SPOUSE_AND_CHILDREN = "all_spouse_and_children"
    const val PLAN_A_USERS = "plan_a_users"
    const val PLAN_B_USERS = "plan_b_users"
    const val VALUE_150 = "150"
    const val BASE_URL = "baseUrl"


}