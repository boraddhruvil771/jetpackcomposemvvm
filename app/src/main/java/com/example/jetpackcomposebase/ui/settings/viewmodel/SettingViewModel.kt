package com.example.jetpackcomposebase.ui.settings.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposebase.base.LocaleManager
import com.example.jetpackcomposebase.utils.MyPreference
import com.example.jetpackcomposebase.utils.PrefKey
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val localeManager: LocaleManager,
    private val myPreference: MyPreference
) : ViewModel() {

    private val _selectedLanguage = mutableStateOf(PrefKey.EN_CODE)
    val selectedLanguage: State<String> = _selectedLanguage

    private val _currentLocale = mutableStateOf(Locale(PrefKey.EN_CODE))
    val currentLocale: State<Locale> = _currentLocale

    init {
        initializeLanguage()
    }

    fun changeLanguage(languageCode: String) {
        _currentLocale.value = Locale(languageCode)
        _selectedLanguage.value = languageCode
        myPreference.setValueString(PrefKey.SELECTED_LANGUAGE, languageCode)
        // Trigger any additional actions needed on language change
    }

    private fun initializeLanguage() {
        val languageCode = myPreference.getValueString(PrefKey.SELECTED_LANGUAGE, PrefKey.EN_CODE)
        changeLanguage(languageCode ?: PrefKey.EN_CODE)
    }
}
