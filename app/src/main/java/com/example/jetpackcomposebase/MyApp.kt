package com.example.jetpackcomposebase

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import com.example.jetpackcomposebase.base.LocaleManager
import com.example.jetpackcomposebase.utils.Constants.DSN
import com.example.jetpackcomposebase.utils.MyPreference
import com.example.jetpackcomposebase.utils.PrefKey
import com.example.jetpackcomposebase.utils.SentryService
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {
    @Inject
    lateinit var mPref: MyPreference

    @Inject
    lateinit var localeManager: LocaleManager

    companion object {
        private var instance: MyApp? = null
        fun applicationContext(): MyApp {
            return instance as MyApp
        }
    }

    fun updateLocale(context: Context, localeCode: String) {
        val locale = Locale(localeCode)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration).apply {
            setLocale(locale)
        }
        context.resources.updateConfiguration(config, context.resources.displayMetrics)

        // Save to preferences
        mPref.setValueString(PrefKey.SELECTED_LANGUAGE, localeCode)

        // Notify activity of the locale change
        if (context is ComponentActivity) {
            context.recreate()
        }
    }

    override fun onCreate() {
        super.onCreate()

        // You need to add your own dsn for sentry log

        SentryService.init(DSN)
        instance = this
    }

    init {
        instance = this
    }
}