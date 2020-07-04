package com.vito.cornelius.data.authentication.token

import com.vito.cornelius.domain.common.repository.IUserSessionRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenAuthInterceptor @Inject constructor(
        private val sessionRepository: IUserSessionRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val userSession = sessionRepository.get()
        val jwtToken = userSession?.authenticationToken?.token
        val request = chain.request()
                .newBuilder()
                // add Authorization key on request header
                // key will be using access token
                .header(HEADER_AUTHORIZATION, "$HEADER_TOKEN_JWT $jwtToken")
                .build()
        return chain.proceed(request)
    }

    private companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_TOKEN_JWT = "jwt"
    }
}