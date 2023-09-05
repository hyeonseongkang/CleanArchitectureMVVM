package com.aos.cleanarchitecturemvvm.domain.usecase

import com.aos.cleanarchitecturemvvm.domain.repository.UserRepository
import com.aos.cleanarchitecturemvvm.domain.model.User

class GetUserUseCase(private val userRepository: UserRepository) {
    fun execute(id: Int): User {
        return userRepository.getUser(id)
    }
}