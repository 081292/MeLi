package com.example.meli.feature.detail.ui

sealed class DetailActions {

    data class FetchItem(val itemId: String) : DetailActions()
}
