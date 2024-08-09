package com.example.jetpackcomposebase.ui.login.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposebase.base.ViewModelBase
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.login.model.LoginResponseModel
import com.example.jetpackcomposebase.ui.login.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
) : ViewModelBase() {



    private val _loginResponse =
        MutableStateFlow<ResponseHandler<ResponseData<LoginResponseModel>?>>(ResponseHandler.Empty)
    val loginResponse: StateFlow<ResponseHandler<ResponseData<LoginResponseModel>?>>
        get() = _loginResponse

    val response: MutableState<LoginResponseModel> = mutableStateOf(LoginResponseModel())


    /**
     * This method is for call Login API.
     */
    fun callLoginAPI(mobileNumber: String, password: String) {
        /*  viewModelScope.launch {
              _loginResponse.postValue(ResponseHandler.Loading)
              _loginResponse.value = repository.callLoginAPI(mobileNumber, password)
          }*/
        viewModelScope.launch {
            repository.callLoginAPI(mobileNumber, password).collect { it ->
                _loginResponse.value = it
            }
        }

    }
}
