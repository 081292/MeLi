package com.example.meli.di

import com.example.meli.feature.sites.data.remote.SitesAPIService
import com.example.meli.feature.sites.data.remote.SitesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(sitesAPIService: SitesAPIService) = SitesRepository(sitesAPIService)
}
