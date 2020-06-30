package com.vito.cornelius.data.network

import com.vito.cornelius.data.network.model.AuthenticationToken
import com.vito.cornelius.data.network.model.UserCreatedResponse
import com.vito.cornelius.data.network.model.UserLoginInfo
import com.vito.cornelius.data.network.model.UserSignUpInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApiService {

    @POST("user/signup")
    suspend fun signUp(@Body user: UserSignUpInfo): UserCreatedResponse

    @POST("user/login")
    suspend fun login(@Body user: UserLoginInfo): AuthenticationToken

    @GET("user/logout")
    suspend fun logout()

    @GET("user/refesh-token")
    suspend fun refreshToken(): AuthenticationToken

    //TODO authenticator class & coroutines
    @GET("user/refesh-token")
    fun refreshTokenCall(): Call<AuthenticationToken>

}
