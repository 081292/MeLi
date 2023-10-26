package com.example.meli.feature.sites.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface SitesAPIService {

    @GET("sites")
    suspend fun getSitesOfService(): Response<List<SitesServiceResponse>>
}
