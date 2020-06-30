package com.vito.cornelius.feature.registration.signin.ui.model

sealed class RegistrationSignInEvent {

    sealed class Navigation : RegistrationSignInEvent() {
        object NavigateToHome : Navigation()
    }

    sealed class Errors : RegistrationSignInEvent() {
        object NoNetwork : Errors()
        object BadCredentials : Errors()
    }
}