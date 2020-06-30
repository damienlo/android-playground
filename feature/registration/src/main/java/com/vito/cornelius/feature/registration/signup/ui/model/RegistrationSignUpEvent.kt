package com.vito.cornelius.feature.registration.signup.ui.model

sealed class RegistrationSignUpEvent {

    sealed class Navigation : RegistrationSignUpEvent() {
        object NavigateToHome : Navigation()
    }

    sealed class Errors : RegistrationSignUpEvent() {
        object NoNetwork : Errors()
        object EmailAlreadyExist : Errors()
        object PasswordTooWeak : Errors()
        object Unexpected : Errors()
    }
}