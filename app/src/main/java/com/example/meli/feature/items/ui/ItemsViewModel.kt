package com.example.meli.feature.items.ui

import androidx.lifecycle.viewModelScope
import com.example.meli.feature.items.ui.ItemsActions
import com.example.meli.feature.items.ui.ItemsState
import com.example.meli.feature.sites.data.remote.SiteDataModel
import com.example.meli.feature.sites.data.remote.SitesRepositoryImpl
import com.example.meli.network.NetworkManager
import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import com.example.meli.ui.viewmodel.base.observeActions
import com.example.meli.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val networkManager: NetworkManager,
    private val sitesRepositoryImpl: SitesRepositoryImpl,
) : MeLiBaseViewModel<ItemsState, ItemsActions>() {

    init {
        this.observeActions(viewModelScope) {
            when (it) {
                is ItemsActions.FetchItems -> fetchItems()
            }
        }
    }

    private fun fetchItems() {
        viewModelScope.launch {
            updateState(ItemsState.Loading)
            when (val fetchSitesResponse = sitesRepositoryImpl.fetchSites()) {
                is ResultWrapper.Success -> {
//                    val siteDataModelList = fetchSitesResponse.value.body()?.toList()
//                    if (!siteDataModelList.isNullOrEmpty()) {
//                        updateState{
//                            SitesState.SitesModelState(mapSiteDataModelListToSiteUIModelList(siteDataModelList.sortedBy { it.name }))
//                        }
//                    }
                }
                is ResultWrapper.GenericError -> {
                    updateState(
                        ItemsState.Error(
                            code = fetchSitesResponse.code, error = fetchSitesResponse.message
                        )
                    )
                }
            }
        }
    }

//    private fun mapSiteDataModelListToSiteUIModelList(listOfSitesDataModel: List<SiteDataModel>): List<SiteUIModel> {
//        val listOfSitesUIModel = mutableListOf<SiteUIModel>()
//        for (site in listOfSitesDataModel) {
//            listOfSitesUIModel.add(
//                SiteUIModel(
//                    default_currency_id = site.default_currency_id, id = site.id, name = site.name
//                )
//            )
//        }
//        return listOfSitesUIModel
//    }
}
