package com.vito.cornelius.feature.registration.autologin.domain

import com.vito.cornelius.domain.common.repository.IAuthenticationRepository
import com.vito.cornelius.feature.registration.autologin.domain.model.AutoLoginFailureException
import javax.inject.Inject

class RegistrationAutoLoginInteractor @Inject constructor(
        private val authenticationRepository: IAuthenticationRepository
) {

    suspend operator fun invoke() {
        try {
            authenticationRepository.autoLogin()
        } catch (ex: Exception) {
            throw AutoLoginFailureException(cause = ex)
        }
    }
}