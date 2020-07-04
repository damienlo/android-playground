package com.vito.cornelius.feature.registration.signin.domain

import com.vito.cornelius.domain.common.repository.IAuthenticationRepository
import com.vito.cornelius.feature.registration.signin.domain.model.SignInFailureException
import java.io.IOException
import javax.inject.Inject

class SignInInteractor @Inject constructor(
        private val authRepository: IAuthenticationRepository
) {

    suspend operator fun invoke(email: String, password: String) {
        try {
            authRepository.login(email, password)
        } catch (ex: Exception) {
            if (ex is IOException) throw ex
            throw SignInFailureException()
        }
    }
}