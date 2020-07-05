package com.vito.cornelius.feature.registration.signup.domain

import com.vito.cornelius.domain.common.repository.IAuthenticationRepository
import javax.inject.Inject

class SignUpInteractor @Inject constructor(
        private val authRepository: IAuthenticationRepository
) {

    suspend operator fun invoke(name: String, email: String, password: String) =
            authRepository.createAccount(name, email, password)
}