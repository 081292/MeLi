package com.example.meli.feature.sites.data.remote

import com.example.meli.feature.sites.data.remote.SitesAPIService

class SitesRepository(private val apiService: SitesAPIService) {

    suspend fun getSitesOfService() = apiService.getSitesOfService()
}
