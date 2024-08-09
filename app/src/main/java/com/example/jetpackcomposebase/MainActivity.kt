package com.example.jetpackcomposebase

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.jetpackcomposebase.base.LocaleManager
import com.example.jetpackcomposebase.utils.DataStoreUtil
import com.example.jetpackcomposebase.utils.MyPreference
import com.example.jetpackcomposebase.utils.PrefKey
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localeManager: LocaleManager

    @Inject
    lateinit var mPref: MyPreference

    @Inject
    lateinit var dataStoreUtil: DataStoreUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val languageCode = mPref.getValueString(PrefKey.SELECTED_LANGUAGE, PrefKey.EN_CODE)
        window.decorView.layoutDirection =
            if (languageCode?.contains(PrefKey.AR_CODE) == true) View.LAYOUT_DIRECTION_RTL else View.LAYOUT_DIRECTION_LTR

        setContent {
            JetpackBaseApp(localeManager)
        }
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun useCustomConfig(context: Context, languageCode: String?): Context? {
        Locale.setDefault(Locale(languageCode!!))
        val config = Configuration(context.resources.configuration).apply {
            setLocale(Locale(languageCode))
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(config)
        } else {
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
            context
        }
    }

    override fun onDestroy() {
        lifecycleScope.launch { dataStoreUtil.clearDataStore() }
        super.onDestroy()
    }
}

