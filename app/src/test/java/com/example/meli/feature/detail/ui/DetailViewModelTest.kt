package com.example.meli.feature.detail.ui

import com.example.meli.MeLiBaseViewModelTest
import com.example.meli.feature.detail.data.remote.DetailDataModel
import com.example.meli.feature.detail.data.remote.DetailRepositoryImpl
import com.example.meli.feature.detail.data.remote.DetailResponse
import com.example.meli.feature.detail.data.remote.PicturesDataModel
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

class DetailViewModelTest : MeLiBaseViewModelTest<DetailActions, DetailViewModel>() {

    private val detailRepositoryImpl: DetailRepositoryImpl = mockk(relaxed = true)

    private val networkManager: NetworkManager = mockk(relaxed = true)

    override fun viewModelProvider(): DetailViewModel = spyk(
        DetailViewModel(
            networkManager = networkManager,
            detailRepositoryImpl = detailRepositoryImpl,
        )
    )

    @Before
    fun setup() {
        setupViewModel()
    }

    @Test
    fun `test fetch item detail`() = runBlocking {
        every { networkManager.online } returns true
        every { networkManager.state.value } returns NetworkState(online = true)
        val detailDateModel =
            DetailDataModel("", "", "", listOf<PicturesDataModel>())
        val responseDataModel = listOf(DetailResponse(detailDateModel))
        val response: Response<List<DetailResponse>> = mockk(relaxed = true) {
            every { body() } returns responseDataModel
        }

        coEvery { detailRepositoryImpl.fetchItem(any()) } returns ResultWrapper.Success(response)

        viewModel.callPrivateFunc("fetchItem", "test")

        verify {
            detailRepositoryImpl["fetchItem"]("test")
        }
        assert(networkManager.state.value.online)
        assert(viewModel.currentState is DetailState.DetailModelState)
    }

    @Test
    fun `test fetch item detail abort on offline`() = runBlocking {
        every { networkManager.online } returns false
        every { networkManager.state.value } returns NetworkState(online = false)
        coEvery { detailRepositoryImpl.fetchItem(any()) } returns ResultWrapper.GenericError(
            code = 0,
            message = "test"
        )

        viewModel.callPrivateFunc("fetchItem", "test")

        verify(exactly = 0) {
            detailRepositoryImpl["fetchItem"]("test")
        }
        assert(!networkManager.state.value.online)
    }
}
