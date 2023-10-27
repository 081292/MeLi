package com.example.meli.feature.sites.data.remote

import retrofit2.Response

interface SitesRepository {

    suspend fun getSitesOfService(): Response<List<SitesServiceResponse>>
}
