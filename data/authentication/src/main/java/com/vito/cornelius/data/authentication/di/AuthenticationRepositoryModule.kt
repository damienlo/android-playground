package com.vito.cornelius.data.authentication.di

import com.vito.cornelius.data.authentication.AuthenticationRepository
import com.vito.cornelius.domain.common.repository.IAuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AuthenticationRepositoryModule {

    @Binds
    abstract fun bindsAuthenticationRepository(
            repository: AuthenticationRepository
    ): IAuthenticationRepository
}