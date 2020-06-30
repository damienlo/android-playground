package com.vito.cornelius.feature.registration.signin.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vito.cornelius.core.android.SingleEvent
import com.vito.cornelius.feature.registration.signin.domain.SignInInteractor
import com.vito.cornelius.feature.registration.signin.ui.model.RegistrationSignInEvent
import kotlinx.coroutines.launch
import java.io.IOException

class RegistrationSignInViewModel @ViewModelInject constructor(
        private val signIn: SignInInteractor
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _event = MutableLiveData<SingleEvent<RegistrationSignInEvent>>()
    val event: LiveData<SingleEvent<RegistrationSignInEvent>> = _event

    fun signInClicked(email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            runCatching {
                signIn(email, password)
            }.onSuccess {
                _loading.value = false
                _event.value = SingleEvent(RegistrationSignInEvent.Navigation.NavigateToHome)
            }.onFailure {
                _loading.value = false
                _event.value = if (it is IOException) {
                    SingleEvent(RegistrationSignInEvent.Errors.NoNetwork)
                } else {
                    SingleEvent(RegistrationSignInEvent.Errors.BadCredentials)
                }
            }
        }
    }
}