package com.example.jetpackcomposebase.ui.signup.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jetpackcomposebase.base.LocaleManager
import com.example.jetpackcomposebase.utils.MyPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewmodel @Inject constructor(
    private val localeManager: LocaleManager,
    private val myPreference: MyPreference
) : ViewModel() {


}
