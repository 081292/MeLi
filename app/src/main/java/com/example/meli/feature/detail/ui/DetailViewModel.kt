package com.example.meli.feature.detail.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.meli.feature.detail.data.remote.DetailRepositoryImpl
import com.example.meli.feature.detail.data.remote.PicturesDataModel
import com.example.meli.network.NetworkManager
import com.example.meli.ui.viewmodel.base.MeLiBaseViewModel
import com.example.meli.ui.viewmodel.base.observeActions
import com.example.meli.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val networkManager: NetworkManager,
    private val detailRepositoryImpl: DetailRepositoryImpl,
) : MeLiBaseViewModel<DetailState, DetailActions>() {

    init {
        this.observeActions(viewModelScope) {
            when (it) {
                is DetailActions.FetchItem -> fetchItem(it.itemId)
            }
        }
        viewModelScope.launch {
            networkManager.state.collectLatest {
                updateState(
                    DetailState.Network(
                        online = it.online
                    )
                )
            }
        }
    }

    private fun fetchItem(itemId: String) {
        if (!networkManager.online) return
        viewModelScope.launch {
            updateState(DetailState.Loading)
            when (val fetchDetailResponse = detailRepositoryImpl.fetchItem(itemId)) {
                is ResultWrapper.Success -> {
                    val detailDataModel = fetchDetailResponse.value.body()?.toList()?.first()
                    updateState(
                        DetailState.DetailModelState(
                            DetailUIModel(
                                id = detailDataModel?.body?.id ?: "",
                                title = detailDataModel?.body?.title ?: "",
                                pictures = mapPicturesDataModelListToPicturesUIModelList(detailDataModel?.body?.pictures),
                                price = detailDataModel?.body?.price ?: "",
                            )
                        )
                    )
                }

                is ResultWrapper.GenericError -> {
                    updateState(
                        DetailState.Error(
                            code = fetchDetailResponse.code, error = fetchDetailResponse.message
                        )
                    )
                }
            }
        }
    }

    private fun mapPicturesDataModelListToPicturesUIModelList(listOfPicturesDataModel: List<PicturesDataModel>?): List<PicturesUIModel> {
        if (listOfPicturesDataModel.isNullOrEmpty()) return emptyList()
        val listOfPicturesUIModel = mutableListOf<PicturesUIModel>()
        for (picture in listOfPicturesDataModel) {
            listOfPicturesUIModel.add(
                PicturesUIModel(
                    id = picture.id,
                    url = picture.url,
                )
            )
        }
        return listOfPicturesUIModel
    }
}
