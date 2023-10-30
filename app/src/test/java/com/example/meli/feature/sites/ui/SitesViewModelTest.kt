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

    private val sitesRepositoryImpl: SitesRepositoryImpl = mockk(relaxed = true)

    private val networkManager: NetworkManager = mockk(relaxed = true)

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
        every { networkManager.online } returns true
        every { networkManager.state.value } returns NetworkState(online = true)
        val siteDateModel =
            SiteDataModel(default_currency_id = "test", id = "test", name = "MEXICO")
        val response: Response<List<SiteDataModel>> = mockk(relaxed = true) {
            every { body() } returns listOf(siteDateModel)
        }

        coEvery { sitesRepositoryImpl.fetchSites() } returns ResultWrapper.Success(response)

        viewModel.callPrivateFunc("fetchSites")

        verify {
            sitesRepositoryImpl["fetchSites"]()
        }
        assert(networkManager.state.value.online)
        assert(viewModel.currentState is SitesState.SitesModelState)
    }

    @Test
    fun `test fetch sites abort on offline`() = runBlocking {
        every { networkManager.online } returns false
        every { networkManager.state.value } returns NetworkState(online = false)
        coEvery { sitesRepositoryImpl.fetchSites() } returns ResultWrapper.GenericError(
            code = 0,
            message = "test"
        )

        viewModel.callPrivateFunc("fetchSites")

        verify(exactly = 0) {
            sitesRepositoryImpl["fetchSites"]()
        }
        assert(!networkManager.state.value.online)
    }
}
