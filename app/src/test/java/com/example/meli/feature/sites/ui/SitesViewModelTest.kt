package com.example.meli.feature.sites.ui

import com.example.meli.MeLiBaseViewModelTest
import com.example.meli.feature.sites.data.remote.SiteDataModel
import com.example.meli.feature.sites.data.remote.SitesRepositoryImpl
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

class SitesViewModelTest : MeLiBaseViewModelTest<SitesActions, SitesViewModel>() {

    private val siteDateModel = SiteDataModel(
        default_currency_id = "",
        id = "",
        name = "MEXICO",
    )

    private val response: Response<List<SiteDataModel>> = mockk(relaxed = true) {
        every { body() } returns listOf(siteDateModel)
    }

    private val sitesRepositoryImpl: SitesRepositoryImpl = mockk(relaxed = true) {
        coEvery { fetchSites() } returns ResultWrapper.Success(response)
    }

    private val networkManager: NetworkManager = mockk(relaxed = true) {
        every { online } returns true
        every { state.value } returns NetworkState(online = true)
    }

    override fun viewModelProvider(): SitesViewModel = spyk(
        SitesViewModel(
            networkManager = networkManager,
            sitesRepositoryImpl = sitesRepositoryImpl,
        )
    )

    @Before
    fun setup() {
        setupViewModel()
    }

    @Test
    fun `test fetch sites`() = runBlocking {

        viewModel.callPrivateFunc("fetchSites")

        verify {
            sitesRepositoryImpl["fetchSites"]()
        }
        assert(networkManager.state.value.online)
        assert(viewModel.currentState is SitesState.SitesModelState)
    }
}