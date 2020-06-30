package com.vito.cornelius.feature.registration.signup.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vito.cornelius.core.android.SingleEvent
import com.vito.cornelius.feature.registration.signup.domain.SignUpInteractor
import com.vito.cornelius.feature.registration.signup.ui.model.RegistrationSignUpEvent
import kotlinx.coroutines.launch
import java.io.IOException

class RegistrationSignUpViewModel @ViewModelInject constructor(
        private val signUp: SignUpInteractor

) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _event = MutableLiveData<SingleEvent<RegistrationSignUpEvent>>()
    val event: LiveData<SingleEvent<RegistrationSignUpEvent>> = _event

    fun signUpClicked(name: String, email: String, password: String) {
        viewModelScope.launch {
            _loading.value = true
            runCatching {
                signUp(name, email, password)
            }.onSuccess {
                _loading.value = false
                _event.value = SingleEvent(RegistrationSignUpEvent.Navigation.NavigateToHome)
            }.onFailure {
                _loading.value = false
                _event.value = if (it is IOException) {
                    SingleEvent(RegistrationSignUpEvent.Errors.NoNetwork)
                } else {
                    SingleEvent(RegistrationSignUpEvent.Errors.Unexpected)
                }
            }
        }
    }
}