package com.example.meli.feature.items.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItemsAPIService {

    @GET("sites/{siteId}/search")
    suspend fun fetchItems(
        @Path("siteId") siteId: String,
        @Query("q") item: String?,
    ): Response<Items>
}