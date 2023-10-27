package com.example.meli.feature.items.data.remote

data class Items(
    val results: List<ItemDataModel>
)

data class ItemDataModel(
    val id: String,
    val title: String,
    val price: String,
    val thumbnail: String,
)
