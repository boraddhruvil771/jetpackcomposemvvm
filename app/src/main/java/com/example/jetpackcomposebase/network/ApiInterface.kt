package com.example.jetpackcomposebase.network

import com.example.jetpackcomposebase.model.UpdateFCMTokenRequest
import com.example.jetpackcomposebase.ui.dashboard.model.DocumentsResponseModel
import com.example.jetpackcomposebase.ui.dashboard.model.GetDirectPrimaryCareResponse
import com.example.jetpackcomposebase.ui.dashboard.model.GetPediatricTelemedicineResponse
import com.example.jetpackcomposebase.ui.dashboard.model.VerifyNotificationRequest
import com.example.jetpackcomposebase.ui.login.model.LoginRequest
import com.example.jetpackcomposebase.ui.login.model.LoginResponseModel
import com.example.jetpackcomposebase.ui.signup.model.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

/**
 * Interface used for api
 */
@Singleton
interface ApiInterface {

    /*
        @GET("characters")
        suspend fun getCharacters(): Response<List<MovieCharacter>>
    */

    @POST(ApiHelper.LOGIN_API)
    suspend fun getLogin(@Body loginRequest: LoginRequest): Response<ResponseData<LoginResponseModel>>

    @POST(ApiHelper.SIGNUP_API)
    suspend fun getSignUp(@Body signUpRequest: SignUpRequest): Response<ResponseData<LoginResponseModel>>

    @POST(ApiHelper.GET_TELEMEDICINE_DETAIL_API)
    suspend fun getPediatricTelemedicineDetail(): Response<ResponseData<GetPediatricTelemedicineResponse>>


    @POST(ApiHelper.REFRESH_TOKEN_API)
    suspend fun getNewToken(@Body verifyNotificationRequest: VerifyNotificationRequest): Response<ResponseData<LoginResponseModel>>

    @POST(ApiHelper.UPDATE_DEVICE_DATA_API)
    suspend fun getUpdateFCMToken(@Body updateFCMTokenRequest: UpdateFCMTokenRequest): Response<ResponseData<LoginResponseModel>>


    @GET(ApiHelper.PRIMARY_CARE_API)
    suspend fun getDirectPrimaryCareDetail(): Response<ResponseData<GetDirectPrimaryCareResponse>>

    @POST(ApiHelper.GET_TELEMEDICINE_API)
    suspend fun getTelemedicineDetail(): Response<ResponseData<GetPediatricTelemedicineResponse>>

    @GET(ApiHelper.DOCUMENTS_API)
    suspend fun getDocuments(): Response<ResponseData<DocumentsResponseModel>>


}
