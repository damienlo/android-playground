package com.vito.cornelius.feature.home.users.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vito.cornelius.feature.home.users.model.UserUiModel

class UserListViewModel @ViewModelInject constructor() : ViewModel() {

    private val _users: MutableLiveData<List<UserUiModel>> = MutableLiveData()
    val users: LiveData<List<UserUiModel>> = _users

    init {
        _users.value = listOf(
                UserUiModel(
                        userId = "1",
                        name = "Corrine Carrez",
                        avatar = "https://upload.wikimedia.org/wikipedia/commons/f/f5/Poster-sized_portrait_of_Barack_Obama.jpg"
                ),
                UserUiModel(
                        userId = "2",
                        name = "Michel Malard",
                        avatar = null
                ),
                UserUiModel(
                        userId = "3",
                        name = "Alain Alba",
                        avatar = "https://storage.googleapis.com/afs-prod/media/4ac3c165cf3a4953a9c44b70821845dc/800.jpeg"
                ),
                UserUiModel(
                        userId = "4",
                        name = "Didier Dumont",
                        avatar = "https://img.timeinc.net/time/photoessays/2009/time_100_influence/boris_johnson.jpg"
                )
        )
    }
}
