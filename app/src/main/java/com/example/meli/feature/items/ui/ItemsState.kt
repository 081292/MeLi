package com.example.meli.feature.items.ui

sealed class ItemsState {

    object Inactive : ItemsState()
    object Loading : ItemsState()
    data class Network(val online: Boolean) : ItemsState()
    data class ItemsModelState(val itemUIModelList: List<ItemUIModel>) : ItemsState()
    data class Error(val code: Int?, val error: String?) : ItemsState()
}
