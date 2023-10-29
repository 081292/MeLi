package com.example.meli.feature.sites.ui

sealed class SitesState {

    object Inactive : SitesState()
    object Loading : SitesState()
    data class Network(val online: Boolean) : SitesState()
    data class SitesModelState(val siteUIModelList: List<SiteUIModel>) : SitesState()
    data class Error(val code: Int?, val error: String?) : SitesState()
}
