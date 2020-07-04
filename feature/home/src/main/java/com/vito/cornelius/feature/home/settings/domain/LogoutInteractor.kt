package com.vito.cornelius.feature.home.settings.domain

import com.vito.cornelius.domain.common.repository.IAuthenticationRepository
import javax.inject.Inject

class LogoutInteractor @Inject constructor(
        private val authenticationRepository: IAuthenticationRepository
) {

    suspend operator fun invoke() {
        authenticationRepository.logout()
    }
}