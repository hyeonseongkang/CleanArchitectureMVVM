package com.aos.cleanarchitecturemvvm.data.repository

import com.aos.cleanarchitecturemvvm.domain.model.Post

interface PostRepository {
    suspend fun writePost(post: Post): Result<Post>
}