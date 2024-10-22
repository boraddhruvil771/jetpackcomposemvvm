package com.example.jetpackcomposebase.ui.profile.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.jetpackcomposebase.base.BaseRepository
import com.example.jetpackcomposebase.network.ApiInterface
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.dashboard.model.GetDirectPrimaryCareResponse
import com.example.jetpackcomposebase.ui.dashboard.model.GetPediatricTelemedicineResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

open class ProfileScreenRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {


    open suspend fun callGetPediatricTelemedicineResponse(
    ): Flow<ResponseHandler<ResponseData<GetPediatricTelemedicineResponse>?>> = flow {
        try {

            val response = apiInterface.getPediatricTelemedicineDetail()

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

/*    open suspend fun callGetDirectPrimaryCareResponse(
    ): Flow<ResponseHandler<ResponseData<GetDirectPrimaryCareResponse>?>> = flow {
        try {

            val response = apiInterface.getDirectPrimaryCareDetail()

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
    }*/

    open suspend fun getTelemedicineDetail(
    ): Flow<ResponseHandler<ResponseData<GetPediatricTelemedicineResponse>?>> = flow {
        try {

            val response = apiInterface.getTelemedicineDetail()

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
