package com.example.jetpackcomposebase.ui.signup.repository

import android.util.Log
import com.example.jetpackcomposebase.base.BaseRepository
import com.example.jetpackcomposebase.network.ApiInterface
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.login.model.LoginResponseModel
import com.example.jetpackcomposebase.ui.signup.model.SignUpRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


open class SignupRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {
    private val TAG = "SignupRepository"

    open suspend fun  callSignUpAPI(signUpRequest: SignUpRequest): ResponseHandler<ResponseData<LoginResponseModel>?> {
        return makeAPICall {
            apiInterface.getSignUp(signUpRequest)
        }
    }


    open suspend fun callSignUpAPIOld(
        ssn: String, mobileNumber: String, password: String
    ): Flow<ResponseHandler<ResponseData<LoginResponseModel>?>> = flow {
        try {
            val signUpRequest = SignUpRequest()
            signUpRequest.userSSN = ssn
            signUpRequest.userContact = mobileNumber
            signUpRequest.userPassword = password
            val response = apiInterface.getSignUp(signUpRequest)

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(ResponseHandler.OnSuccessResponse(it))
                } ?: emit(ResponseHandler.OnFailed(response.code(), "Empty response body", ""))
            } else {
                emit(ResponseHandler.OnFailed(response.code(), response.message(), ""))
            }
        } catch (e: HttpException) {
            Log.e(TAG, "HttpException: ${e.localizedMessage}", e)
            emit(
                ResponseHandler.OnFailed(
                    e.code(), "An unexpected error occurred: ${e.localizedMessage}", ""
                )
            )
        } catch (e: IOException) {
            Log.e(TAG, "IOException: ${e.localizedMessage}", e)
            emit(
                ResponseHandler.OnFailed(
                    500, "Couldn't reach server. Check your internet connection.", ""
                )
            )
        } catch (e: Exception) {
            Log.e(TAG, "Exception: ${e.localizedMessage}", e)
            emit(
                ResponseHandler.OnFailed(
                    500, "An unknown error occurred: ${e.localizedMessage}", ""
                )
            )
        }
    }

}