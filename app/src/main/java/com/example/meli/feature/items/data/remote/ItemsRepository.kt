package com.example.meli.feature.items.data.remote

import com.example.meli.utils.ResultWrapper
import retrofit2.Response

interface ItemsRepository {

    suspend fun fetchItems(siteId: Int, item: String): ResultWrapper<Response<Items>>
}
