package com.vito.cornelius.feature.registration.autologin.ui.model

sealed class RegistrationAutoLoginEvent {
    object NavigateToHome : RegistrationAutoLoginEvent()
    object NavigateToSignIn : RegistrationAutoLoginEvent()
}