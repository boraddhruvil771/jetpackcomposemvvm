package com.example.jetpackcomposebase.utils

import androidx.datastore.preferences.core.stringPreferencesKey
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object Constants {
    val JSON = jacksonObjectMapper()
    var KEY_BUNDLE = "bundle"

    var KEY_GRAPH_ID = "graphId"
    var KEY_START_DESTINATION = "startDestination"

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