package com.example.meli.feature.items.ui

import com.example.meli.MeLiBaseViewModelTest
import com.example.meli.feature.items.data.remote.ItemDataModel
import com.example.meli.feature.items.data.remote.Items
import com.example.meli.feature.items.data.remote.ItemsRepositoryImpl
import com.example.meli.network.NetworkManager
import com.example.meli.network.NetworkState
import com.example.meli.utils.ResultWrapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ItemsViewModelTest : MeLiBaseViewModelTest<ItemsActions, ItemsViewModel>() {

    private val itemsRepositoryImpl: ItemsRepositoryImpl = mockk(relaxed = true)

    private val networkManager: NetworkManager = mockk(relaxed = true)

    override fun viewModelProvider(): ItemsViewModel = spyk(
        ItemsViewModel(
            networkManager = networkManager,
            itemsRepositoryImpl = itemsRepositoryImpl,
        )
    )

    @Before
    fun setup() {
        setupViewModel()
    }

    @Test
    fun `test fetch items`() = runBlocking {
        every { networkManager.online } returns true
        every { networkManager.state.value } returns NetworkState(online = true)
        val itemDateModel =
            ItemDataModel("", "", "", "")
        val items = Items(listOf(itemDateModel))
        val response: Response<Items> = mockk(relaxed = true) {
            every { body() } returns items
        }

        coEvery { itemsRepositoryImpl.fetchItems(any(), any()) } returns ResultWrapper.Success(response)

        viewModel.callPrivateFunc("fetchItems", "test", "test")

        verify {
            itemsRepositoryImpl["fetchItems"]("test", "test")
        }
        assert(networkManager.state.value.online)
        assert(viewModel.currentState is ItemsState.ItemsModelState)
    }

    @Test
    fun `test fetch items abort on offline`() = runBlocking {
        every { networkManager.online } returns false
        every { networkManager.state.value } returns NetworkState(online = false)
        coEvery { itemsRepositoryImpl.fetchItems(any(), any()) } returns ResultWrapper.GenericError(
            code = 0,
            message = "test"
        )

        viewModel.callPrivateFunc("fetchItems", "test", "test")

        verify(exactly = 0) {
            itemsRepositoryImpl["fetchItems"]("test", "test")
        }
        assert(!networkManager.state.value.online)
    }
}