package com.example.meli.feature.detail.data.remote

import com.example.meli.utils.ResultWrapper
import com.example.meli.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailAPIService: DetailAPIService
) : DetailRepository {

    override suspend fun fetchItem(itemId: String): ResultWrapper<Response<List<DetailResponse>>> {
        return safeApiCall(Dispatchers.IO) {
            detailAPIService.fetchItem(ids = itemId)
        }
    }
}
