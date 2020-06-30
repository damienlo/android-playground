package com.vito.cornelius.feature.home.settings.logout

import javax.inject.Inject

class LogoutInteractor @Inject constructor(
        private val logoutRepository: ILogoutRepository
) {

    suspend operator fun invoke() {
        logoutRepository.logout()
    }
}