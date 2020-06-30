package com.vito.cornelius.data.network.di

import android.content.Context
import android.content.SharedPreferences
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.vito.cornelius.data.network.BuildConfig
import com.vito.cornelius.data.network.ServiceConstants
import com.vito.cornelius.data.network.UserApiService
import com.vito.cornelius.data.network.auth.TokenAuthInterceptor
import com.vito.cornelius.data.network.auth.TokenAuthenticator
import com.vito.cornelius.data.network.session.JwtSessionRepository
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Call
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
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Named("sessionPreferences")
    fun providesSessionPreferences(@ApplicationContext context: Context): SharedPreferences =
            context.getSharedPreferences("session_preferences", Context.MODE_PRIVATE)

    @Provides
    fun providesTokenAuthenticator(
            sessionRepository: JwtSessionRepository,
            userApiService: UserApiService
    ): TokenAuthenticator = TokenAuthenticator(sessionRepository, userApiService)

    @Provides
    fun providesTokenAuthInterceptor(
            sessionRepository: JwtSessionRepository
    ): TokenAuthInterceptor = TokenAuthInterceptor(sessionRepository)

    @Provides
    fun providesOkHttpClient(
            tokenInterceptor: TokenAuthInterceptor,
            tokenAuthenticator: TokenAuthenticator
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

    @Provides
    fun providesApiService(retrofit: Retrofit): UserApiService =
            retrofit.create(UserApiService::class.java)
}