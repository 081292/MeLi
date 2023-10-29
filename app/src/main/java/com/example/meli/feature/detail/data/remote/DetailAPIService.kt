package com.example.meli.feature.detail.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailAPIService {

    @GET("items")
    suspend fun fetchItem(
        @Query("ids") ids: String?,
    ): Response<List<DetailResponse>>
}