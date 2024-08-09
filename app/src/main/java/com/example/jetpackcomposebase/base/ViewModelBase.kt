package com.example.jetpackcomposebase.base

import androidx.lifecycle.ViewModel
import com.example.jetpackcomposebase.utils.DebugLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


open class ViewModelBase : ViewModel() {

    /*
    *    The objective of either of these is to keep the mutability private,
     *   so consumers of this class do not accidentally update the MutableLiveData themselves.
     */
   /* private val _snackBarMessage = MutableLiveData<Any>()
    val snackBarMessage: LiveData<Any>  get() = _snackBarMessage*/

    private val _snackBarMessage = MutableStateFlow("")
    var snackBarMessage: StateFlow<String> = _snackBarMessage.asStateFlow()

    private val _progressBarDisplay = MutableStateFlow(false)
    var progressBarDisplay: StateFlow<Boolean> = _progressBarDisplay.asStateFlow()

    private val _toolBarModel = MutableStateFlow(ToolbarModel())
    var toolBarModel: StateFlow<ToolbarModel> = _toolBarModel.asStateFlow()


    private val _hideKeyBoardEvent = MutableStateFlow(false)
    var hideKeyBoardEvent: StateFlow<Boolean> = _hideKeyBoardEvent.asStateFlow()

    private val _bottomBarVisibility = MutableStateFlow(false)
    var bottomBarVisibility: StateFlow<Boolean> = _bottomBarVisibility.asStateFlow()


    /**
     * This method is used to display the Snake Bar Message
     *
     * @param message string object to display.
     */

    fun showBottomBar(isHide: Boolean) {
        _bottomBarVisibility.value = true
        DebugLog.e("bottomBarVV>>>: ${_bottomBarVisibility.value}")
    }


    fun showSnackBarMessage(message: String) {
        _snackBarMessage.value = message
    }

    fun showProgressBar(display: Boolean) {
        _progressBarDisplay.value = display

    }

    fun setToolbarItems(toolbarModel: ToolbarModel) {
        _toolBarModel.value = toolbarModel
    }

    fun hideKeyboard() {
        _hideKeyBoardEvent.value = true
    }

}
