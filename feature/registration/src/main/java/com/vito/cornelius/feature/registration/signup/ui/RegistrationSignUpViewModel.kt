package com.vito.cornelius.feature.registration.signup.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vito.cornelius.core.android.SingleEvent
import com.vito.cornelius.domain.common.model.Resource
import com.vito.cornelius.feature.registration.signup.domain.SignUpInteractor
import com.vito.cornelius.feature.registration.signup.ui.model.RegistrationSignUpEvent
import kotlinx.coroutines.launch

class RegistrationSignUpViewModel @ViewModelInject constructor(
        private val signUp: SignUpInteractor

) : ViewModel() {

    private val _status = MutableLiveData<Resource<Unit>>()
    val status: LiveData<Resource<Unit>> = _status

    private val _event = MutableLiveData<SingleEvent<RegistrationSignUpEvent>>()
    val event: LiveData<SingleEvent<RegistrationSignUpEvent>> = _event

    fun signUpClicked(name: String, email: String, password: String) {
        viewModelScope.launch {
            _status.value = Resource.Loading()
            val result = signUp(name, email, password)
            _status.value = result
        }
    }
}