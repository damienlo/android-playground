package com.vito.cornelius.feature.registration.common.di

import com.vito.cornelius.feature.registration.common.data.RegistrationAuthRepository
import com.vito.cornelius.feature.registration.domain.IAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RegistrationModule {

    @Binds
    abstract fun bindsIAuthRepository(authRepository: RegistrationAuthRepository): IAuthRepository
}