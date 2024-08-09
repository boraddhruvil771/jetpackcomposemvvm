package com.example.jetpackcomposebase.ui.dashboard.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposebase.base.ViewModelBase
import com.example.jetpackcomposebase.network.ApiInterface
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.dashboard.model.GetPediatricTelemedicineResponse
import com.example.jetpackcomposebase.ui.dashboard.model.MovieCharacter
import com.example.jetpackcomposebase.ui.dashboard.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val apiInterface: ApiInterface
) : ViewModelBase() {


    private val _state =
        MutableStateFlow<ResponseHandler<List<MovieCharacter>>>(ResponseHandler.Empty)
    val state: StateFlow<ResponseHandler<List<MovieCharacter>>>
        get() = _state


    init {
        viewModelScope.launch {
            homeRepository.getCharacters().collect { characters ->
                _state.value = characters
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


//    private val _telemedicineResponse =
//        MutableStateFlow<ResponseHandler<ResponseData<GetPediatricTelemedicineResponse>?>>(
//            ResponseHandler.Empty
//        )
//    val telemedicineResponse: StateFlow<ResponseHandler<ResponseData<GetPediatricTelemedicineResponse>?>>
//        get() = _telemedicineResponse
//
//    /**
//     * This method is for call pediatric telemedicine detail API.
//     */
//    fun callGetTelemedicineDetail() {
//
//        viewModelScope.launch {
//            homeRepository.callGetPediatricTelemedicineResponse().collect { it ->
//                _telemedicineResponse.value = it
//            }
//        }
//    }
//
//
//    private val _getPediatricTelemedicineDetail =
//        MutableStateFlow<ResponseHandler<ResponseData<GetDirectPrimaryCareResponse>?>>(
//            ResponseHandler.Empty
//        )
//    val getPediatricTelemedicineDetail: StateFlow<ResponseHandler<ResponseData<GetDirectPrimaryCareResponse>?>>
//        get() = _getPediatricTelemedicineDetail
//
//    /**
//     * This method is for call pediatric telemedicine detail API.
//     */
//    fun callPediatricTelemedicineDetail() {
//
//        viewModelScope.launch {
//            homeRepository.getPediatricTelemedicineDetail().collect { it ->
//                _getPediatricTelemedicineDetail.value = it
//            }
//        }
//    }
}