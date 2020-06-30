package com.vito.cornelius.feature.home.settings.data

import com.vito.cornelius.data.network.UserApiService
import com.vito.cornelius.data.network.session.JwtSessionRepository
import com.vito.cornelius.feature.home.settings.logout.ILogoutRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogoutRepository @Inject constructor(
        private val userApiService: UserApiService,
        private val sessionRepository: JwtSessionRepository
) : ILogoutRepository {

    override suspend fun logout() {
        sessionRepository.clearSession()
        fireAndForgetSilently {
            userApiService.logout()
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
}