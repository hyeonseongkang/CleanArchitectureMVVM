package com.aos.cleanarchitecturemvvm.domain.repository

import com.aos.cleanarchitecturemvvm.domain.model.Post

interface PostRepository {
    suspend fun writePost(post: Post): Post

    suspend fun getPosts(): List<Post>

    suspend fun deletePost(post: Post): Int

    suspend fun searchPost(query: String): List<Post>
}