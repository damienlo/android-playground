package com.vito.cornelius.data.session

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
object SessionModule {

    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Named("sessionPreferences")
    fun providesSessionPreferences(@ApplicationContext context: Context): SharedPreferences =
            context.getSharedPreferences("session_preferences", Context.MODE_PRIVATE)
}