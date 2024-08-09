package com.example.jetpackcomposebase.ui.signup.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.login.model.LoginResponseModel
import com.example.jetpackcomposebase.ui.signup.repository.SignupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewmodel @Inject constructor(
    private val repository: SignupRepository,

    ) : ViewModel() {

    private val _signupResponse =
        MutableStateFlow<ResponseHandler<ResponseData<LoginResponseModel>?>>(ResponseHandler.Empty)
    val signupResponse: StateFlow<ResponseHandler<ResponseData<LoginResponseModel>?>>
        get() = _signupResponse

    val response: MutableState<LoginResponseModel> = mutableStateOf(LoginResponseModel())


    /**
     * This method is for call Login API.
     */
    fun callSignup(ssn: String, mobileNumber: String, password: String) {

        viewModelScope.launch {
            repository.callSignUpAPIOld(ssn, mobileNumber, password).collect { it ->
                _signupResponse.value = it
            }
        }

    }

}
