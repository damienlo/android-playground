package com.vito.cornelius.data.network.session

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.vito.cornelius.data.network.model.AuthenticationToken
import javax.inject.Inject
import javax.inject.Named

class JwtSessionRepository @Inject constructor(
        private val moshi: Moshi,
        @Named("sessionPreferences") private val sessionPrefs: SharedPreferences
) {

    fun saveSession(authenticationToken: AuthenticationToken) {
        val adapter = moshi.adapter<AuthenticationToken>(AuthenticationToken::class.java)
        val jsonAuthenticationToken = adapter.toJson(authenticationToken)
        sessionPrefs.edit().putString(KEY_SESSION, jsonAuthenticationToken).apply()
    }

    fun getSession(): AuthenticationToken? {
        val adapter = moshi.adapter<AuthenticationToken>(AuthenticationToken::class.java)
        val jsonAuthenticationToken = sessionPrefs.getString(KEY_SESSION, null)
        return jsonAuthenticationToken?.let { adapter.fromJson(it) }
    }

    fun clearSession() {
        sessionPrefs.edit().remove(KEY_SESSION).apply()
    }

    private companion object {
        private const val KEY_SESSION = "KEY_SESSION"
    }
}

