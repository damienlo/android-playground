package com.vito.cornelius.data.authentication.di

import com.vito.cornelius.data.authentication.AuthenticationService
import com.vito.cornelius.data.authentication.RefreshTokenService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
class AuthenticationModule {

    @Provides
    fun providesAuthenticationService(retrofit: Retrofit): AuthenticationService =
            retrofit.create(AuthenticationService::class.java)

    @Provides
    fun providesRefreshTokenService(retrofit: Retrofit): RefreshTokenService =
            retrofit.create(RefreshTokenService::class.java)
}