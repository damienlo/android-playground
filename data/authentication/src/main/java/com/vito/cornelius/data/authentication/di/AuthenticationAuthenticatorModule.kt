package com.vito.cornelius.data.authentication.di

import com.vito.cornelius.data.authentication.token.TokenAuthInterceptor
import com.vito.cornelius.data.authentication.token.TokenAuthenticator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
abstract class AuthenticationAuthenticatorModule {

    @Binds
    @Named("tokenInterceptor")
    abstract fun bindsTokenAuthInterceptor(interceptor: TokenAuthInterceptor): Interceptor

    @Binds
    @Named("tokenAuthenticator")
    abstract fun bindsTokenAuthenticator(authenticator: TokenAuthenticator): Authenticator
}