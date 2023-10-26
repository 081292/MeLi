package com.example.meli.ui.viewmodel

sealed class MainActions {

    object NetworkConnectionEstablished : MainActions()
    object NetworkConnectionLost : MainActions()
}
