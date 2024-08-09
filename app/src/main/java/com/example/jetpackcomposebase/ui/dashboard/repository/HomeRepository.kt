package com.example.jetpackcomposebase.ui.dashboard.repository

import com.example.jetpackcomposebase.base.BaseRepository
import com.example.jetpackcomposebase.network.ApiInterface
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.dashboard.model.MovieCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

open class HomeRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {


    fun getCharacters(): Flow<ResponseHandler<List<MovieCharacter>>> = flow {
        emit(ResponseHandler.Loading)
        try {
            val response = apiInterface.getCharacters()
            if (response.isSuccessful) {
                val characters = response.body() ?: emptyList()
                emit(ResponseHandler.OnSuccessResponse(characters))
            } else {
                emit(ResponseHandler.OnFailed(response.code(), "Error message", "Error code"))
            }
        } catch (e: Exception) {
            emit(ResponseHandler.OnError(e.message ?: "Unknown error"))
        }
    }

    /*fun getCharacters(): Flow<List<MovieCharacter>> = flow {
        val characters = apiInterface.getCharacter()
        emit(characters)
    }*/
}

