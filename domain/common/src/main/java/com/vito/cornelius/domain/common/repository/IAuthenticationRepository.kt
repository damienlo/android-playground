package com.vito.cornelius.domain.common.repository

interface IAuthenticationRepository {

    suspend fun createAccount(name: String, email: String, password: String)

    suspend fun login(email: String, password: String)

    suspend fun autoLogin()

    suspend fun logout()
}