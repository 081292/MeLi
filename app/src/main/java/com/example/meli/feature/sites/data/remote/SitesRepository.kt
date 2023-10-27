package com.example.meli.feature.sites.data.remote

import com.example.meli.utils.ResultWrapper
import retrofit2.Response

interface SitesRepository {

    suspend fun fetchSites(): ResultWrapper<Response<List<SiteDataModel>>>
}
