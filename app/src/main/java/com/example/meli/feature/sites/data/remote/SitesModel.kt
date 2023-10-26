package com.example.meli.feature.sites.data.remote

data class SitesServiceResponse(
    val sites: List<Site>
)

data class Site(
    val default_currency_id: String,
    val id: String,
    val name: String
)
