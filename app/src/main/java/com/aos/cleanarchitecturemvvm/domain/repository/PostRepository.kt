package com.aos.cleanarchitecturemvvm.domain.repository

import com.aos.cleanarchitecturemvvm.domain.model.Post

interface PostRepository {
    suspend fun writePost(post: Post): Post
}