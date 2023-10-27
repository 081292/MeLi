package com.example.meli.feature.items.data.remote

import com.example.meli.utils.ResultWrapper
import com.example.meli.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(
    private val itemsAPIService: ItemsAPIService
) : ItemsRepository {

    override suspend fun fetchItems(siteId: Int, item: String): ResultWrapper<Response<Items>> {
        return safeApiCall(Dispatchers.IO) {
            itemsAPIService.fetchItems(siteId = siteId, item = item)
        }
    }
}
