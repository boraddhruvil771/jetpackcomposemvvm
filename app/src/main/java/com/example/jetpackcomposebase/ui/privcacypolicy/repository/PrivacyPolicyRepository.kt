package com.example.jetpackcomposebase.ui.privcacypolicy.repository

import com.example.jetpackcomposebase.base.BaseRepository
import com.example.jetpackcomposebase.network.ApiInterface
import javax.inject.Inject

open class PrivacyPolicyRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {}