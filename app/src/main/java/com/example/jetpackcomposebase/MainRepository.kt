package com.example.jetpackcomposebase

import com.example.jetpackcomposebase.base.BaseRepository
import com.example.jetpackcomposebase.network.ApiInterface
import javax.inject.Inject

open class MainRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {

}