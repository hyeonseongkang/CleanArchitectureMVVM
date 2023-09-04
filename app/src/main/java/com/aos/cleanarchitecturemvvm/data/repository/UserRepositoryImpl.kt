package com.aos.cleanarchitecturemvvm.data.repository

import com.aos.cleanarchitecturemvvm.data.datasource.LocalDataSource
import com.aos.cleanarchitecturemvvm.domain.model.User

class UserRepositoryImpl(private val localDataSource: LocalDataSource) : UserRepository {
    override fun getUser(id: Int): User {
        return localDataSource.getUser(id)
    }
    override fun updateUserEmail(id: Int, email: String): User {
        return localDataSource.updateUserEmail(id, email)
    }

}