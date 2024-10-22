package com.example.jetpackcomposebase.ui.profile.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposebase.base.ViewModelBase
import com.example.jetpackcomposebase.network.ApiInterface
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.dashboard.model.GetPediatricTelemedicineResponse
import com.example.jetpackcomposebase.ui.profile.repository.ProfileScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewmodel @Inject constructor(
    private val homeRepository: ProfileScreenRepository,
    private val apiInterface: ApiInterface
) : ViewModelBase() {

    init {
        viewModelScope.launch {
            homeRepository.callGetPediatricTelemedicineResponse().collect { characters ->
                _getDirectPrimaryCareResponse.value = characters
            }
            homeRepository.getTelemedicineDetail().collect { characters ->
                _telemedicineResponse.value = characters
            }

        }
    }

    private val _getDirectPrimaryCareResponse =
        MutableStateFlow<ResponseHandler<ResponseData<GetPediatricTelemedicineResponse>?>>(
            ResponseHandler.Empty
        )
    val getDirectPrimaryCareResponse: StateFlow<ResponseHandler<ResponseData<GetPediatricTelemedicineResponse>?>>
        get() = _getDirectPrimaryCareResponse

    /**
     * This method is for call pediatric telemedicine detail API.
     */
    fun callGetDirectPrimaryCareResponse() {

        viewModelScope.launch {
            homeRepository.callGetPediatricTelemedicineResponse().collect { it ->
                _getDirectPrimaryCareResponse.value = it
            }
        }
    }


    private val _telemedicineResponse =
        MutableStateFlow<ResponseHandler<ResponseData<GetPediatricTelemedicineResponse>?>>(
            ResponseHandler.Empty
        )
    val telemedicineResponse: StateFlow<ResponseHandler<ResponseData<GetPediatricTelemedicineResponse>?>>
        get() = _telemedicineResponse

    /**
     * This method is for call pediatric telemedicine detail API.
     */
    fun callGetTelemedicineDetail() {

        viewModelScope.launch {
            homeRepository.callGetPediatricTelemedicineResponse().collect { it ->
                _telemedicineResponse.value = it
            }
        }
    }


    /*    private val _getPediatricTelemedicineDetail =
            MutableStateFlow<ResponseHandler<ResponseData<GetDirectPrimaryCareResponse>?>>(
                ResponseHandler.Empty
            )
        val getPediatricTelemedicineDetail: StateFlow<ResponseHandler<ResponseData<GetDirectPrimaryCareResponse>?>>
            get() = _getPediatricTelemedicineDetail


        fun callPediatricTelemedicineDetail() {

            viewModelScope.launch {
                homeRepository.callGetPediatricTelemedicineResponse().collect { it ->
                    _getPediatricTelemedicineDetail.value = it
                }
            }
        }*/
}