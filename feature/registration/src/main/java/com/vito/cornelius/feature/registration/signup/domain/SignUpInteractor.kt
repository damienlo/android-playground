package com.vito.cornelius.feature.registration.signup.domain

import com.vito.cornelius.feature.registration.domain.IAuthRepository
import com.vito.cornelius.feature.registration.signup.domain.model.SignUpFailureException
import java.io.IOException
import javax.inject.Inject

class SignUpInteractor @Inject constructor(
        private val authRepository: IAuthRepository
) {

    suspend operator fun invoke(name: String, email: String, password: String) {
        try {
            authRepository.createAccount(name, email, password)
        } catch (ex: Exception) {
            if (ex is IOException) throw ex
            throw SignUpFailureException()
        }
    }
}