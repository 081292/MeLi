package com.example.meli.feature.detail.data.remote

import com.example.meli.utils.ResultWrapper
import retrofit2.Response

interface DetailRepository {

    suspend fun fetchItem(itemId: String): ResultWrapper<Response<List<DetailResponse>>>
}
