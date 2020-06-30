package com.vito.cornelius.feature.home.settings.ui.model

sealed class SettingsEvent {
    sealed class Navigation : SettingsEvent() {
        object NavigateToRegistration : Navigation()
    }
}