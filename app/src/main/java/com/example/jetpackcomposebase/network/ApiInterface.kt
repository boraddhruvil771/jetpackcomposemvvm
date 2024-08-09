package com.example.jetpackcomposebase.network

import com.example.jetpackcomposebase.ui.dashboard.model.MovieCharacter
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

    @GET("characters")
    suspend fun getCharacters(): Response<List<MovieCharacter>>

    @POST(ApiHelper.LOGIN_API)
    suspend fun getLogin(@Body loginRequest: LoginRequest): Response<ResponseData<LoginResponseModel>>

    @POST(ApiHelper.SIGNUP_API)
    suspend fun getSignUp(@Body signUpRequest: SignUpRequest): Response<ResponseData<LoginResponseModel>>

}
