package com.example.firstapplication.viewModel

import androidx.lifecycle.ViewModel
import com.example.firstapplication.base.PermissionManager
import com.example.firstapplication.utils.PermissionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileImageViewModel @Inject constructor(
    private val pm: PermissionManager
) : ViewModel() {

    private val _state = MutableStateFlow<PermissionState>(PermissionState.LOADING)
    val state: StateFlow<PermissionState> = _state

    private val _shouldCameraOpenKey = MutableStateFlow<Boolean>(false)
    val shouldCameraOpenKey: StateFlow<Boolean> = _shouldCameraOpenKey

    fun checkPermission(permission: String) {
        _state.value =
            if (pm.isGranted(permission)) PermissionState.GRANTED
            else PermissionState.NEED_REQUEST
        _shouldCameraOpenKey.value = !_shouldCameraOpenKey.value
    }


    private val _recomposeTrigger = MutableStateFlow<Int>(1)
    val recomposeTrigger:  StateFlow<Int> = _recomposeTrigger

    fun triggerTopBarRecompose() {
        _recomposeTrigger.value = _recomposeTrigger.value++
    }
}

//    private val _recompuseTrigger  = MutableStateFlow<Boolean>(true)
//    val recomposeTrigger : StateFlow<Boolean> = _recompuseTrigger
//
//    fun recompuseScaffoldTopbar(){
//       _recompuseTrigger.value = !_recompuseTrigger.value
//    }
//}
///////////////
//  private val _recomposeTrigger = MutableStateFlow(Unit)
//    val recomposeTrigger: StateFlow<Unit> = _recomposeTrigger
//
//    fun triggerTopBarRecompose() {
//        _recomposeTrigger.value = Unit
//    }