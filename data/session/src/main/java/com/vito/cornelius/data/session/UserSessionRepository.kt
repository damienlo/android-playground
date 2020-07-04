package com.vito.cornelius.data.session

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.vito.cornelius.data.session.model.SerializedSession
import com.vito.cornelius.domain.common.model.AuthenticationToken
import com.vito.cornelius.domain.common.model.UserSession
import javax.inject.Inject
import javax.inject.Named

class UserSessionRepository @Inject constructor(
        private val moshi: Moshi,
        @Named("sessionPreferences") private val sessionPrefs: SharedPreferences
) {

    fun save(userSession: UserSession) {
        val adapter = moshi.adapter<SerializedSession>(SerializedSession::class.java)
        val jsonUserSession = adapter.toJson(userSession.toSerializedSession())
        sessionPrefs.edit().putString(KEY_USER_SESSION, jsonUserSession).apply()
    }

    fun save(newAuthenticationToken: AuthenticationToken) {
        get()?.let { currentSession ->
            val newSession = currentSession.copy(authenticationToken = newAuthenticationToken)
            save(newSession)
        }
    }

    fun get(): UserSession? {
        val adapter = moshi.adapter<SerializedSession>(SerializedSession::class.java)
        val jsonSerializedSession = sessionPrefs.getString(KEY_USER_SESSION, null)
        return jsonSerializedSession?.let { adapter.fromJson(it)?.toUserSession() }
    }

    fun clear() {
        sessionPrefs.edit().remove(KEY_USER_SESSION).apply()
    }

    private fun UserSession.toSerializedSession(): SerializedSession = SerializedSession(
            userId = this.userId,
            createdAt = this.authenticationToken.createdAt,
            token = this.authenticationToken.token,
            expiresIn = this.authenticationToken.expiresIn
    )

    private fun SerializedSession.toUserSession(): UserSession = UserSession(
            userId = this.userId,
            authenticationToken = AuthenticationToken(
                    createdAt = this.createdAt,
                    token = this.token,
                    expiresIn = this.expiresIn
            )
    )

    private companion object {
        private const val KEY_USER_SESSION = "KEY_SESSION"
    }
}