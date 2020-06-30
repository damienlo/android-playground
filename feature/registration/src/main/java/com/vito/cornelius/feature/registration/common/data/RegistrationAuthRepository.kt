package com.vito.cornelius.feature.registration.common.data

import com.vito.cornelius.data.network.UserApiService
import com.vito.cornelius.data.network.model.UserLoginInfo
import com.vito.cornelius.data.network.model.UserSignUpInfo
import com.vito.cornelius.data.network.session.JwtSessionRepository
import com.vito.cornelius.feature.registration.domain.IAuthRepository
import javax.inject.Inject

class RegistrationAuthRepository @Inject constructor(
        private val userApiService: UserApiService,
        private val sessionRepository: JwtSessionRepository
) : IAuthRepository {

    override suspend fun autoLogin() {
        val authenticationToken = userApiService.refreshToken()
        sessionRepository.saveSession(authenticationToken = authenticationToken)
    }

    override suspend fun login(email: String, password: String) {
        val loginInfo = UserLoginInfo(email = email, password = password)
        val authenticationToken = userApiService.login(loginInfo)
        sessionRepository.saveSession(authenticationToken = authenticationToken)
    }

    override suspend fun createAccount(name: String, email: String, password: String) {
        val userInfo = UserSignUpInfo(name = name, email = email, password = password)
        val userCreatedResponse = userApiService.signUp(userInfo)
        sessionRepository.saveSession(authenticationToken = userCreatedResponse.token)
    }
}