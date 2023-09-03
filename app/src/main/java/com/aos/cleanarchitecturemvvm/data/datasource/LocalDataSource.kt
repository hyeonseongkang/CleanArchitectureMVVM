package com.aos.cleanarchitecturemvvm.data.datasource

import com.aos.cleanarchitecturemvvm.domain.model.User

class LocalDataSource {
    fun getUser(id: Int): User {
        // 데이터베이스 또는 로컬 저장소에서 사용자 정보 가져오기
        return User(id, "Sample User")
    }
}