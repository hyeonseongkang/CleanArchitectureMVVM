package com.aos.cleanarchitecturemvvm.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aos.cleanarchitecturemvvm.domain.model.User
import com.aos.cleanarchitecturemvvm.domain.usecase.GetUserUseCase
import com.aos.cleanarchitecturemvvm.domain.usecase.UpdateUserEmailUseCase

class MainViewModel(private val getUserUseCase: GetUserUseCase,
                    private val updateUserEmailUseCase: UpdateUserEmailUseCase
) : ViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun getUser(id: Int) {
        _user.value = getUserUseCase.execute(id)
    }

    fun updateUserEmail(id: Int, email: String) {
        _user.value = updateUserEmailUseCase.execute(id, email)
    }
}