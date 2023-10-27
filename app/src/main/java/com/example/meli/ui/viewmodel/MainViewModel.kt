package com.example.meli.ui.viewmodel

import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import com.example.meli.network.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val networkManager: NetworkManager,
) : MeLiBaseViewModel<MainState, MainActions>() {

}
