package com.aos.cleanarchitecturemvvm.data.repository

import com.aos.cleanarchitecturemvvm.data.local.LocalPostDataSource
import com.aos.cleanarchitecturemvvm.domain.model.Post
import com.aos.cleanarchitecturemvvm.domain.repository.PostRepository

class PostRepositoryImpl(private val postDataSource: LocalPostDataSource): PostRepository {
    override suspend fun writePost(post: Post): Post {
        return postDataSource.writePost(post)
    }
}