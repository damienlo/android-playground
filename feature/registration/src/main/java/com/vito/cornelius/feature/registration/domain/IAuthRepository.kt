package com.vito.cornelius.feature.registration.domain

interface IAuthRepository {

    suspend fun autoLogin()

    suspend fun login(email: String, password: String)

    suspend fun createAccount(name: String, email: String, password: String)
}