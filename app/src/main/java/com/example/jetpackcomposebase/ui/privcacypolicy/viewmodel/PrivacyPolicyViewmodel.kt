package com.example.jetpackcomposebase.ui.privcacypolicy.viewmodel

import com.example.jetpackcomposebase.base.ViewModelBase
import com.example.jetpackcomposebase.ui.login.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrivacyPolicyViewmodel @Inject constructor(
    private val repository: LoginRepository,
) : ViewModelBase() {

}