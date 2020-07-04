package com.vito.cornelius.data.network.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vito.cornelius.data.network.BuildConfig
import com.vito.cornelius.data.network.ServiceConstants
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Authenticator
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun providesOkHttpClient(
            @Named("tokenInterceptor") tokenInterceptor: Interceptor,
            @Named("tokenAuthenticator") tokenAuthenticator: Authenticator
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(providesLoggingInterceptor())
            .addInterceptor(tokenInterceptor)
            .authenticator(tokenAuthenticator)
            .build()

    private fun providesLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }

    @Provides
    @Singleton
    fun providesRetrofit(
            client: Lazy<OkHttpClient>
    ): Retrofit = Retrofit.Builder()
            .baseUrl(ServiceConstants.BASE_URL)
            .callFactory(object : Call.Factory {
                override fun newCall(request: Request): Call =
                        client.get().newCall(request)
            })
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
}