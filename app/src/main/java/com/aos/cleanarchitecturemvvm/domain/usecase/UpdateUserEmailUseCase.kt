package com.aos.cleanarchitecturemvvm.domain.usecase

import com.aos.cleanarchitecturemvvm.data.repository.UserRepository
import com.aos.cleanarchitecturemvvm.domain.model.User

class UpdateUserEmailUseCase(private val userRepository: UserRepository) {
    fun execute(id: Int, email: String): User {
        return userRepository.updateUserEmail(id, email)
    }
}