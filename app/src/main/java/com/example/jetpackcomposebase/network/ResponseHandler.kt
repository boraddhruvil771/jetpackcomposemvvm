package com.example.jetpackcomposebase.network

sealed class ResponseHandler<out T> {
    object Empty : ResponseHandler<Nothing>()
    object Loading : ResponseHandler<Nothing>()
    class OnFailed(val code: Int, val message: String, val messageCode: String) :
        ResponseHandler<Nothing>()

    class OnSuccessResponse<T>(val response: T) : ResponseHandler<T>()
    class OnError(val message: String) : ResponseHandler<Nothing>()  // Update to use String directly

}