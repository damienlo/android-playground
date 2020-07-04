package com.vito.cornelius.domain.common.repository

import com.vito.cornelius.domain.common.model.AuthenticationToken
import com.vito.cornelius.domain.common.model.UserSession

interface IUserSessionRepository {
    fun get(): UserSession?
    fun save(userSession: UserSession)
    fun save(newAuthenticationToken: AuthenticationToken)
    fun clear()
}