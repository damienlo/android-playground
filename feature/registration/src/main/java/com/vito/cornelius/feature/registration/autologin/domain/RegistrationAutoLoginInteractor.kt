package com.vito.cornelius.feature.registration.autologin.domain

import com.vito.cornelius.domain.common.model.Resource
import com.vito.cornelius.domain.common.repository.IAuthenticationRepository
import javax.inject.Inject

class RegistrationAutoLoginInteractor @Inject constructor(
        private val authenticationRepository: IAuthenticationRepository
) {

    suspend operator fun invoke(): Resource<Unit> =
            authenticationRepository.autoLogin()
}