package com.example.meli.feature.items.ui

sealed class ItemsActions {

    data class FetchItems(val siteId: String, val item: String) : ItemsActions()
}
