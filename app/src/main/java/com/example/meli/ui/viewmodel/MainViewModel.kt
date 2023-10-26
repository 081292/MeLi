package com.example.meli.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import com.example.meli.utils.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val networkManager: NetworkManager) :
    MeLiBaseViewModel<MainState, MainActions>() {

    override fun buildInitialState(): MainState {
        TODO("Not yet implemented")
    }

    init {
        viewModelScope.launch {
            networkManager.state.collectLatest { networkState ->
                if (networkState.online) {
                    sendAction(MainActions.NetworkConnectionEstablished)
                } else {
                    sendAction(MainActions.NetworkConnectionLost)
                }
            }
        }
    }
}
