package com.vito.cornelius.data.authentication

import com.vito.cornelius.data.authentication.model.response.AuthenticationTokenResponse
import retrofit2.Call
import retrofit2.http.GET

interface RefreshTokenService {

    //TODO authenticator class & coroutines
    @GET("user/refesh-token")
    fun refreshTokenCall(): Call<AuthenticationTokenResponse>
}