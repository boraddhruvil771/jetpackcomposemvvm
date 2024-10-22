package com.example.jetpackcomposebase

import com.example.jetpackcomposebase.base.BaseRepository
import com.example.jetpackcomposebase.model.UpdateFCMTokenRequest
import com.example.jetpackcomposebase.network.ApiInterface
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.dashboard.model.VerifyNotificationRequest
import com.example.jetpackcomposebase.ui.login.model.LoginResponseModel
import javax.inject.Inject

open class MainRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {

    open suspend fun callUpdateFCMTokenAPI(updateFCMTokenRequest: UpdateFCMTokenRequest): ResponseHandler<ResponseData<LoginResponseModel>?> {
        return makeAPICall {
            apiInterface.getUpdateFCMToken(updateFCMTokenRequest)
        }
    }

    open suspend fun callGetNewTokenAPI(verifyNotificationRequest: VerifyNotificationRequest): ResponseHandler<ResponseData<LoginResponseModel>?> {
        return makeAPICall {
            apiInterface.getNewToken(verifyNotificationRequest)
        }
    }
}