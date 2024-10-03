package com.example.jetpackcomposebase.ui.dashboard.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposebase.base.ViewModelBase
import com.example.jetpackcomposebase.network.ApiInterface
import com.example.jetpackcomposebase.network.ResponseData
import com.example.jetpackcomposebase.network.ResponseHandler
import com.example.jetpackcomposebase.ui.dashboard.model.DocumentsResponseModel
import com.example.jetpackcomposebase.ui.dashboard.model.GetPediatricTelemedicineResponse
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



    init {
        viewModelScope.launch {
            homeRepository.callGetPediatricTelemedicineResponse().collect { characters ->
                _getDirectPrimaryCareResponse.value = characters
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


    private val _documentsResponse =
        MutableStateFlow<ResponseHandler<ResponseData<DocumentsResponseModel>?>>(
            ResponseHandler.Empty
        )

    val documentResponse: StateFlow<ResponseHandler<ResponseData<DocumentsResponseModel>?>>
        get() = _documentsResponse

    /**
     * This method is for call documents API.
     */
    fun callDocumentsAPI() {
        viewModelScope.launch {

            homeRepository.callDocumentsAPI().collect { it ->
                _documentsResponse.value = it
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