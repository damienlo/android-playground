package com.vito.cornelius.feature.registration.signup.ui.model

sealed class RegistrationSignUpEvent {

    sealed class Navigation : RegistrationSignUpEvent() {
        object NavigateToHome : Navigation()
    }
}