package com.example.meli.feature.sites.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class SitesRepositoryImpl @Inject constructor(
    private val sitesAPIService: SitesAPIService
) : SitesRepository {

    override suspend fun getSitesOfService(): Response<List<SitesServiceResponse>> =
        withContext(Dispatchers.IO) {
            sitesAPIService.getSitesOfService()
        }
}
