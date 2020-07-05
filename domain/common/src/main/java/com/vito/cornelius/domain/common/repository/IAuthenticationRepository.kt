package com.vito.cornelius.domain.common.repository

import com.vito.cornelius.domain.common.model.Resource

interface IAuthenticationRepository {

    suspend fun createAccount(name: String, email: String, password: String): Resource<Unit>

    suspend fun login(email: String, password: String): Resource<Unit>

    suspend fun autoLogin(): Resource<Unit>

    suspend fun logout(): Resource<Unit>
}