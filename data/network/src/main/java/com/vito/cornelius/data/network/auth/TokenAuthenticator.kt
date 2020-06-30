package com.vito.cornelius.data.network.auth

import android.util.Log
import com.vito.cornelius.data.network.UserApiService
import com.vito.cornelius.data.network.model.AuthenticationToken
import com.vito.cornelius.data.network.session.JwtSessionRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
        private val sessionRepository: JwtSessionRepository,
        private val userApiService: UserApiService
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= MAX_AUTH_RETRY) {
            return null
        }

        var newAccessToken: AuthenticationToken? = null
        val call = userApiService.refreshTokenCall()
        try {
            val responseCall = call.execute()
            newAccessToken = responseCall.body()
        } catch (ex: Exception) {
            Log.d(TokenAuthenticator::class.simpleName, "error on TokenAuthenticator: $ex")
        }

        // retry the failed 401 request with new access token
        if (newAccessToken != null) {
            sessionRepository.saveSession(authenticationToken = newAccessToken)
            return response.request.newBuilder()
                    .header(HEADER_AUTHORIZATION, "$HEADER_TOKEN_JWT $newAccessToken")
                    .build()
        }
        return null
    }

    private fun responseCount(response: Response?): Int {
        var result = 1
        var checkResponse = response
        while (checkResponse != null) {
            checkResponse = checkResponse.priorResponse
            result++
        }
        return result
    }

    private companion object {
        private const val MAX_AUTH_RETRY = 3
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_TOKEN_JWT = "jwt"
    }
}
