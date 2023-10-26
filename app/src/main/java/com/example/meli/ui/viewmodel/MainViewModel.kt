package com.example.meli.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.meli.feature.sites.data.remote.SitesRepository
import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import com.example.meli.network.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val networkManager: NetworkManager, private val sitesRepository: SitesRepository) :
    MeLiBaseViewModel<MainState, MainActions>() {

    override fun buildInitialState(): MainState {
        TODO("Not yet implemented")
    }

    init {
        viewModelScope.launch {
            networkManager.state.collectLatest { networkState ->
                if (networkState.online) {
                    sendAction(MainActions.NetworkConnectionEstablished)
                    val sites = sitesRepository.getSitesOfService()
                    Log.d("TAG", sites.toString())
                } else {
                    sendAction(MainActions.NetworkConnectionLost)
                }
            }
        }
    }
}
