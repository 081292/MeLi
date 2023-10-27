package com.example.meli.feature.sites.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.meli.feature.sites.data.remote.SitesRepositoryImpl
import com.example.meli.network.NetworkManager
import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SitesViewModel @Inject constructor(
    private val networkManager: NetworkManager,
    private val sitesRepositoryImpl: SitesRepositoryImpl,
) :
    MeLiBaseViewModel<SitesState, SitesActions>() {

    override fun buildInitialState(): SitesState {
        TODO("Not yet implemented")
    }

    init {
        Log.d("TAG", "Sites Init")
        viewModelScope.launch {
            networkManager.state.collectLatest { networkState ->
                if (networkState.online) {
                    val sites = sitesRepositoryImpl.getSitesOfService()
                    Log.d("TAG", sites.toString())
                }
            }
        }
    }
}