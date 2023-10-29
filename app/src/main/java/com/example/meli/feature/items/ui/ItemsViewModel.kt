package com.example.meli.feature.items.ui

import androidx.lifecycle.viewModelScope
import com.example.meli.feature.items.data.remote.ItemDataModel
import com.example.meli.feature.items.data.remote.ItemsRepositoryImpl
import com.example.meli.network.NetworkManager
import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import com.example.meli.ui.viewmodel.base.observeActions
import com.example.meli.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val networkManager: NetworkManager,
    private val itemsRepositoryImpl: ItemsRepositoryImpl,
) : MeLiBaseViewModel<ItemsState, ItemsActions>() {

    init {
        this.observeActions(viewModelScope) {
            when (it) {
                is ItemsActions.FetchItems -> fetchItems(it.siteId, it.item)
            }
        }
        viewModelScope.launch {
            networkManager.state.collectLatest {
                updateState(
                    ItemsState.Network(
                        online = it.online
                    )
                )
            }
        }
    }

    private fun fetchItems(siteId: String, item: String) {
        if (!networkManager.online) return
        viewModelScope.launch {
            updateState(ItemsState.Loading)
            when (val fetchItemsResponse = itemsRepositoryImpl.fetchItems(siteId, item)) {
                is ResultWrapper.Success -> {
                    val itemsDataModelList = fetchItemsResponse.value.body()?.results
                    if (!itemsDataModelList.isNullOrEmpty()) {
                        updateState {
                            ItemsState.ItemsModelState(
                                mapItemDataModelListToItemUIModelList(itemsDataModelList)
                            )
                        }
                    }
                }

                is ResultWrapper.GenericError -> {
                    updateState(
                        ItemsState.Error(
                            code = fetchItemsResponse.code, error = fetchItemsResponse.message
                        )
                    )
                }
            }
        }
    }

    private fun mapItemDataModelListToItemUIModelList(listOfSitesDataModel: List<ItemDataModel>): List<ItemUIModel> {
        val listOfSitesUIModel = mutableListOf<ItemUIModel>()
        for (item in listOfSitesDataModel) {
            listOfSitesUIModel.add(
                ItemUIModel(
                    id = item.id,
                    title = item.title,
                    price = item.price,
                    thumbnail = item.thumbnail,
                )
            )
        }
        return listOfSitesUIModel
    }
}
