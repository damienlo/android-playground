package com.vito.cornelius.feature.home.settings.di

import android.content.Context
import com.vito.cornelius.feature.home.settings.data.DarkThemeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object SettingsModule {

    @Provides
    fun providesDarkThemeRepository(
            @ApplicationContext context: Context
    ): DarkThemeRepository {
        val sharedPreferences =
                context.getSharedPreferences("default_preferences", Context.MODE_PRIVATE)
        return DarkThemeRepository(
                sharedPreferences
        )
    }
}