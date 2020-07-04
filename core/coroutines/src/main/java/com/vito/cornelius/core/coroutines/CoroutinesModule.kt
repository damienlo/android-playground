package com.vito.cornelius.core.coroutines

import com.vito.cornelius.core.coroutines.dispatchers.DispatcherDefault
import com.vito.cornelius.core.coroutines.dispatchers.DispatcherIO
import com.vito.cornelius.core.coroutines.dispatchers.DispatcherMain
import com.vito.cornelius.core.coroutines.dispatchers.DispatcherUnconfined
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ApplicationComponent::class)
object CoroutinesModule {

    @Provides
    @DispatcherIO
    fun providesDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @DispatcherMain
    fun providesDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @DispatcherDefault
    fun providesDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @DispatcherUnconfined
    fun providesDispatcherUnConfined(): CoroutineDispatcher = Dispatchers.Unconfined
}