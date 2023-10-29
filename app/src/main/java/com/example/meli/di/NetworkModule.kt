package com.example.meli.di

import android.content.Context
import com.example.meli.feature.detail.data.remote.DetailAPIService
import com.example.meli.feature.items.data.remote.ItemsAPIService
import com.example.meli.feature.sites.data.remote.SitesAPIService
import com.example.meli.network.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 *  [NetworkModule] class is the module that will provide the instances of Retrofit and API Services consumed in the app.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.mercadolibre.com/"

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideSitesApiService(retrofit: Retrofit): SitesAPIService = retrofit.create(SitesAPIService::class.java)

    @Singleton
    @Provides
    fun provideItemsApiService(retrofit: Retrofit): ItemsAPIService = retrofit.create(ItemsAPIService::class.java)

    @Singleton
    @Provides
    fun provideDetailApiService(retrofit: Retrofit): DetailAPIService = retrofit.create(DetailAPIService::class.java)

    @Singleton
    @Provides
    fun providesNetworkManager(
        @ApplicationContext context: Context,
    ): NetworkManager {
        return NetworkManager(context = context)
    }
}
