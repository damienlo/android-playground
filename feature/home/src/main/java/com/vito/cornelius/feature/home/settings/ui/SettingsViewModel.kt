package com.vito.cornelius.feature.home.settings.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vito.cornelius.core.android.SingleEvent
import com.vito.cornelius.feature.home.settings.data.DarkThemeRepository
import com.vito.cornelius.feature.home.settings.domain.LogoutInteractor
import com.vito.cornelius.feature.home.settings.ui.model.SettingsEvent
import kotlinx.coroutines.launch

class SettingsViewModel @ViewModelInject constructor(
        private val logout: LogoutInteractor,
        private val darkThemeRepository: DarkThemeRepository
) : ViewModel() {

    private val _event = MutableLiveData<SingleEvent<SettingsEvent>>()
    val event: LiveData<SingleEvent<SettingsEvent>> = _event

    val isDarkThemeLive: LiveData<Boolean> = darkThemeRepository.isDarkThemeLive

    fun logoutButtonClicked() {
        viewModelScope.launch {
            logout()
            _event.value = SingleEvent(SettingsEvent.Navigation.NavigateToRegistration)
        }
    }

    fun updateDarkThemeStatus(enabled: Boolean) {
        darkThemeRepository.isDarkTheme = enabled
    }
}