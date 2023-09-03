package com.aos.cleanarchitecturemvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aos.cleanarchitecturemvvm.domain.model.User
import com.aos.cleanarchitecturemvvm.domain.usecase.GetUserUseCase

class MainViewModel(private val getUserUseCase: GetUserUseCase) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun getUser(id: Int) {
        _user.value = getUserUseCase.execute(id)
    }
}