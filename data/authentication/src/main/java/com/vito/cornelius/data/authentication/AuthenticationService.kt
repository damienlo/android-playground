package com.vito.cornelius.data.authentication

import com.vito.cornelius.data.authentication.model.request.UserLoginBodyRequest
import com.vito.cornelius.data.authentication.model.request.UserSignUpBodyRequest
import com.vito.cornelius.data.authentication.model.response.AuthenticationTokenResponse
import com.vito.cornelius.data.authentication.model.response.UserCreatedResponse
import com.vito.cornelius.data.authentication.model.response.UserLoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationService {

    @POST("user/signup")
    suspend fun signUp(@Body user: UserSignUpBodyRequest): UserCreatedResponse

    @POST("user/login")
    suspend fun login(@Body user: UserLoginBodyRequest): UserLoginResponse

    @GET("user/logout")
    suspend fun logout()

    @GET("user/refesh-token")
    suspend fun refreshToken(): AuthenticationTokenResponse
}
