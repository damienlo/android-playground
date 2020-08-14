package com.vito.cornelius.feature.registration.autologin.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vito.cornelius.core.android.SingleEvent
import com.vito.cornelius.domain.common.model.Resource
import com.vito.cornelius.feature.registration.autologin.domain.RegistrationAutoLoginInteractor
import com.vito.cornelius.feature.registration.autologin.ui.model.RegistrationAutoLoginEvent
import kotlinx.coroutines.launch

class RegistrationAutoLoginViewModel @ViewModelInject constructor(
        private val autoLogin: RegistrationAutoLoginInteractor
) : ViewModel() {

    private val _event = MutableLiveData<SingleEvent<RegistrationAutoLoginEvent>>()
    val event: LiveData<SingleEvent<RegistrationAutoLoginEvent>> = _event

    init {
        viewModelScope.launch {
            val result = autoLogin()
            if (result is Resource.Success<*>) {
                _event.value = SingleEvent(RegistrationAutoLoginEvent.NavigateToHome)
            } else {
                _event.value = SingleEvent(RegistrationAutoLoginEvent.NavigateToSignIn)
            }
        }
    }
}