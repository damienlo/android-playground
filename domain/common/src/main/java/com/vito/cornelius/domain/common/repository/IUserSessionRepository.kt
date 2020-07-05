package com.vito.cornelius.domain.common.repository

import com.vito.cornelius.domain.common.model.authentication.AuthenticationToken
import com.vito.cornelius.domain.common.model.authentication.UserSession

interface IUserSessionRepository {
    fun get(): UserSession?
    fun save(userSession: UserSession)
    fun save(newAuthenticationToken: AuthenticationToken)
    fun clear()
}