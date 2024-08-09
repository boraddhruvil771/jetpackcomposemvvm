package com.example.jetpackcomposebase.ui.signup.repository

import com.example.jetpackcomposebase.base.BaseRepository
import com.example.jetpackcomposebase.network.ApiInterface
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.login.model.LoginResponseModel
import com.example.jetpackcomposebase.ui.signup.model.SignUpRequest
import javax.inject.Inject


open class SignupRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {

    open suspend fun callSignUpAPI(signUpRequest: SignUpRequest): ResponseHandler<ResponseData<LoginResponseModel>?> {
        return makeAPICall {
            apiInterface.getSignUp(signUpRequest)
        }
    }
}