package com.vito.cornelius.data.authentication

import com.vito.cornelius.data.authentication.model.request.UserLoginBodyRequest
import com.vito.cornelius.data.authentication.model.request.UserSignUpBodyRequest
import com.vito.cornelius.data.authentication.model.response.AuthenticationTokenResponse
import com.vito.cornelius.data.authentication.model.response.UserCreatedResponse
import com.vito.cornelius.data.authentication.model.response.UserLoginResponse
import com.vito.cornelius.domain.common.model.AuthenticationToken
import com.vito.cornelius.domain.common.model.UserSession
import com.vito.cornelius.domain.common.repository.IAuthenticationRepository
import com.vito.cornelius.domain.common.repository.IUserSessionRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
        private val authenticationService: AuthenticationService,
        private val sessionRepository: IUserSessionRepository
) : IAuthenticationRepository {

    override suspend fun createAccount(name: String, email: String, password: String) {
        val userInfo = UserSignUpBodyRequest(
                name = name,
                email = email,
                password = password
        )
        val userCreatedResponse = authenticationService.signUp(userInfo)
        sessionRepository.save(userSession = userCreatedResponse.toUserSession())
    }

    override suspend fun login(email: String, password: String) {
        val loginInfo = UserLoginBodyRequest(
                email = email,
                password = password
        )
        val userLoginResponse = authenticationService.login(loginInfo)
        sessionRepository.save(userSession = userLoginResponse.toUserSession())
    }

    override suspend fun autoLogin() {
        val authenticationToken = authenticationService.refreshToken()
        sessionRepository.save(newAuthenticationToken = authenticationToken.toAuthenticationToken())
    }

    override suspend fun logout() {
        sessionRepository.clear()
        fireAndForgetSilently {
            authenticationService.logout()
        }
    }

    // launch new coroutine in background and continue, catch any exception
    private suspend fun fireAndForgetSilently(action: suspend () -> Unit) = GlobalScope.launch {
        try {
            action.invoke()
        } catch (ex: Exception) {
            // do nothing
        }
    }

    private fun UserCreatedResponse.toUserSession() = UserSession(
            userId = this.userId,
            authenticationToken = token.toAuthenticationToken()
    )

    private fun UserLoginResponse.toUserSession() = UserSession(
            userId = this.userId,
            authenticationToken = token.toAuthenticationToken()
    )

    private fun AuthenticationTokenResponse.toAuthenticationToken() = AuthenticationToken(
            createdAt = this.createdAt,
            token = this.token,
            expiresIn = this.expiresIn
    )
}