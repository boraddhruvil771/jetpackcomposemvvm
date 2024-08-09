package com.example.jetpackcomposebase.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.activity.ComponentActivity
import com.example.jetpackcomposebase.MyApp
import com.example.jetpackcomposebase.utils.DebugLog
import com.example.jetpackcomposebase.utils.MyPreference
import com.example.jetpackcomposebase.utils.PrefKey
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Set Locale in application
 */

class LocaleManager() {

    @Inject
    lateinit var mPref: MyPreference

    @SuppressLint("ObsoleteSdkInt")
    private fun updateResources(context: Context, language: String): Context {
        DebugLog.print("languages:$language")
        val config = context.resources.configuration
        val locale = Locale(language)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocale(locale)
        } else {
            config.locale = locale
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(locale)
            return context.createConfigurationContext(config)
        } else {
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            return context
        }
    }

    fun setLocale(activity: ComponentActivity) {
        val languageCode = MyApp.applicationContext().mPref.getValueString(PrefKey.SELECTED_LANGUAGE, PrefKey.EN_CODE)
        Locale.setDefault(Locale(languageCode))
        val config = Configuration(activity.resources.configuration).apply {
            setLocale(Locale(languageCode))
        }
        activity.resources.updateConfiguration(config, activity.resources.displayMetrics)
        activity.recreate()
    }

    fun setNewLocale(context: Context, language: String) {
        DebugLog.e("setNewLocale $language")
        mPref.setValueString(PrefKey.SELECTED_LANGUAGE, language)
        val newContext = updateResources(context, language)
        restartActivity(newContext)
    }

    private fun restartActivity(context: Context) {
        val activity = context as? Activity ?: return
        val intent = activity.intent
        activity.finish()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.startActivity(intent)
    }
}

