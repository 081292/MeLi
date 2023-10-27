package com.example.meli.ui.viewmodel

import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import com.example.meli.network.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val networkManager: NetworkManager) :
    MeLiBaseViewModel<SplashState, SplashActions>() {

    init {
        networkManager.setupNetworkManager()
        CoroutineScope(Dispatchers.IO).launch {
            delay(2500)
            sendAction(SplashActions.NetworkSetupCompleted)
        }
    }
}
