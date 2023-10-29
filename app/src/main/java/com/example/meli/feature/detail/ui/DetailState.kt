package com.example.meli.feature.detail.ui

sealed class DetailState {

    object Inactive : DetailState()
    object Loading : DetailState()
    data class Network(val online: Boolean) : DetailState()
    data class DetailModelState(val detailUIModel: DetailUIModel) : DetailState()
    data class Error(val code: Int?, val error: String?) : DetailState()
}
