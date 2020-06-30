package com.vito.cornelius.data.network.auth

import com.vito.cornelius.data.network.session.JwtSessionRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenAuthInterceptor @Inject constructor(
        private val sessionRepository: JwtSessionRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = sessionRepository.getSession()
        val request = chain.request()
                .newBuilder()
                // add Authorization key on request header
                // key will be using access token
                .header(HEADER_AUTHORIZATION, "$HEADER_TOKEN_JWT ${accessToken?.token}")
                .build()
        return chain.proceed(request)
    }

    private companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_TOKEN_JWT = "jwt"
    }
}