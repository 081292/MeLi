package com.example.meli.feature.detail.data.remote

data class DetailResponse(
    val body: DetailDataModel,
)

data class DetailDataModel(
    val id: String,
    val title: String,
    val price: String,
    val pictures: List<PicturesDataModel>,
)

data class PicturesDataModel(
    val id: String,
    val url: String,
)
