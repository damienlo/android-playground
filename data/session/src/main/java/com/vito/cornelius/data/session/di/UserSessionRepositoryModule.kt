package com.vito.cornelius.data.session.di

import com.vito.cornelius.data.session.UserSessionRepository
import com.vito.cornelius.domain.common.repository.IUserSessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class UserSessionRepositoryModule {

    @Binds
    abstract fun bindsUserSessionRepository(
            repository: UserSessionRepository
    ): IUserSessionRepository
}