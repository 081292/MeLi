package com.example.meli.feature.detail.ui

data class DetailUIModel(
    val id: String,
    val title: String,
    val price: String,
    val pictures: List<PicturesUIModel>,
)

data class PicturesUIModel(
    val id: String,
    val url: String,
)
