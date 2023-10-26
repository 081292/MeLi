package com.example.meli.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NetworkManager(
    context: Context
) {

    private val _state = MutableStateFlow(NetworkState())
    val state: StateFlow<NetworkState> get() = _state

    val online: Boolean get() = state.value.online

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkRequest = NetworkRequest.Builder().build()

    internal val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            _state.value = _state.value.copy(
                online = true
            )
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            _state.value = _state.value.copy(
                online = false
            )
        }
    }

    fun setupNetworkManager() {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }
}
