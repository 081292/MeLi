package com.example.meli

import android.app.Application
import com.example.meli.network.NetworkManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MeLi : Application() {

    @Inject
    lateinit var networkManager: NetworkManager

    override fun onCreate() {
        super.onCreate()

    }
}
