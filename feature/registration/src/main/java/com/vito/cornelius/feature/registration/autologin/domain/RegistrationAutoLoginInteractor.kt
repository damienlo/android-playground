package com.vito.cornelius.feature.registration.autologin.domain

import com.vito.cornelius.data.network.UserApiService
import com.vito.cornelius.data.network.session.JwtSessionRepository
import com.vito.cornelius.feature.registration.autologin.domain.model.AutoLoginFailureException
import com.vito.cornelius.feature.registration.autologin.domain.model.MissingCredentialException
import javax.inject.Inject

class RegistrationAutoLoginInteractor @Inject constructor(
        private val userApiService: UserApiService,
        private val sessionRepository: JwtSessionRepository
) {

    suspend operator fun invoke() {
        if (sessionRepository.getSession() == null) {
            throw MissingCredentialException()
        }
        try {
            val authenticationToken = userApiService.refreshToken()
            sessionRepository.saveSession(authenticationToken)
        } catch (ex: Exception) {
            throw AutoLoginFailureException(cause = ex)
        }
    }
}