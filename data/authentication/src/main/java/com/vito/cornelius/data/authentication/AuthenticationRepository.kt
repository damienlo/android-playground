package com.vito.cornelius.data.authentication

import com.vito.cornelius.data.authentication.model.request.UserLoginBodyRequest
import com.vito.cornelius.data.authentication.model.request.UserSignUpBodyRequest
import com.vito.cornelius.data.authentication.model.response.AuthenticationTokenResponse
import com.vito.cornelius.data.authentication.model.response.UserCreatedResponse
import com.vito.cornelius.data.authentication.model.response.UserLoginResponse
import com.vito.cornelius.data.network.model.ApiError
import com.vito.cornelius.data.network.model.NetworkResponse
import com.vito.cornelius.data.network.model.onSuccess
import com.vito.cornelius.data.network.safeApiCall
import com.vito.cornelius.domain.common.model.ErrorCause
import com.vito.cornelius.domain.common.model.Resource
import com.vito.cornelius.domain.common.model.authentication.AuthenticationToken
import com.vito.cornelius.domain.common.model.authentication.UserSession
import com.vito.cornelius.domain.common.repository.IAuthenticationRepository
import com.vito.cornelius.domain.common.repository.IUserSessionRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
        private val authenticationService: AuthenticationService,
        private val sessionRepository: IUserSessionRepository
) : IAuthenticationRepository {

    override suspend fun createAccount(
            name: String,
            email: String,
            password: String
    ): Resource<Unit> {
        val userInfo = UserSignUpBodyRequest(name = name, email = email, password = password)
        val networkResponse = safeApiCall {
            authenticationService.signUp(userInfo)
        }.onSuccess { userCreatedResponse ->
            sessionRepository.save(userSession = userCreatedResponse.toUserSession())
        }
        return networkResponse.toResource()
    }

    override suspend fun login(email: String, password: String): Resource<Unit> {
        val loginInfo = UserLoginBodyRequest(email = email, password = password)
        val networkResponse = safeApiCall {
            authenticationService.login(loginInfo)
        }.onSuccess { userLoginResponse ->
            sessionRepository.save(userSession = userLoginResponse.toUserSession())
        }
        return networkResponse.toResource()
    }

    override suspend fun autoLogin(): Resource<Unit> {
        val networkResponse = safeApiCall {
            authenticationService.refreshToken()
        }.onSuccess { authenticationToken ->
            sessionRepository.save(newAuthenticationToken = authenticationToken.toAuthenticationToken())
        }
        return networkResponse.toResource()
    }

    override suspend fun logout(): Resource<Unit> {
        sessionRepository.clear()
        fireAndForgetSilently {
            authenticationService.logout()
        }
        return Resource.Success(Unit)
    }

    // launch new coroutine in background and continue, catch any exception
    private suspend fun fireAndForgetSilently(action: suspend () -> Unit) = GlobalScope.launch() {
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

    private fun NetworkResponse<*>.toResource(): Resource<Unit> = when (this) {
        is NetworkResponse.Success -> Resource.Success(Unit)
        is NetworkResponse.Failure -> Resource.Error<Unit>(this.error.toErrorCause())
    }

    private fun ApiError.toErrorCause(): ErrorCause = when (this) {
        is ApiError.NoNetwork -> ErrorCause.NetworkNotAvailable
        is ApiError.Timeout -> ErrorCause.NetworkNotAvailable
        is ApiError.Server -> ErrorCause.ServerError(
                errorCode = this.errorCode,
                key = "TODO",
                message = this.body ?: "TODO"
        )
    }
}
