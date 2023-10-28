package com.example.meli.di

import com.example.meli.feature.items.data.remote.ItemsAPIService
import com.example.meli.feature.items.data.remote.ItemsRepository
import com.example.meli.feature.items.data.remote.ItemsRepositoryImpl
import com.example.meli.feature.sites.data.remote.SitesAPIService
import com.example.meli.feature.sites.data.remote.SitesRepository
import com.example.meli.feature.sites.data.remote.SitesRepositoryImpl
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
    fun providesSitesRepository(sitesAPIService: SitesAPIService): SitesRepository = SitesRepositoryImpl(sitesAPIService)

    @Singleton
    @Provides
    fun providesItemsRepository(itemsAPIService: ItemsAPIService): ItemsRepository = ItemsRepositoryImpl(itemsAPIService)
}
