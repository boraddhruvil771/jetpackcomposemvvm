package com.example.jetpackcomposebase.ui.dashboard.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposebase.base.ViewModelBase
import com.example.jetpackcomposebase.network.ApiInterface
import com.example.jetpackcomposebase.network.ResponseHandler
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

/*    private val _state = MutableStateFlow(emptyList<MovieCharacter>())
    val state: StateFlow<List<MovieCharacter>>
        get() = _state*/

    private val _state = MutableStateFlow<ResponseHandler<List<MovieCharacter>>>(ResponseHandler.Empty)
    val state: StateFlow<ResponseHandler<List<MovieCharacter>>>
        get() = _state


    init {
        viewModelScope.launch {
            homeRepository.getCharacters().collect { characters ->
                _state.value = characters
            }
        }
    }
}