package com.vito.cornelius.feature.home.settings.logout

interface ILogoutRepository {
    suspend fun logout()
}