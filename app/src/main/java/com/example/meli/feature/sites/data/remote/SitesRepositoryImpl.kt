package com.example.meli.feature.sites.data.remote

import com.example.meli.utils.ResultWrapper
import com.example.meli.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class SitesRepositoryImpl @Inject constructor(
    private val sitesAPIService: SitesAPIService
) : SitesRepository {

    override suspend fun fetchSites(): ResultWrapper<Response<List<SiteDataModel>>> {
        return safeApiCall(Dispatchers.IO) {
            sitesAPIService.fetchSites()
        }
    }
}
