package com.aos.cleanarchitecturemvvm.data.repository

import com.aos.cleanarchitecturemvvm.domain.model.User

interface UserRepository {
    fun getUser(id: Int): User

    fun updateUserEmail(id: Int, email: String): User
}