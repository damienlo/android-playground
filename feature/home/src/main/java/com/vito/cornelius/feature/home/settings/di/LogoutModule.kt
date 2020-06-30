package com.vito.cornelius.feature.home.settings.di

import com.vito.cornelius.feature.home.settings.data.LogoutRepository
import com.vito.cornelius.feature.home.settings.logout.ILogoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class LogoutModule {

    @Binds
    abstract fun bindsLogoutRepository(logoutRepository: LogoutRepository): ILogoutRepository
}