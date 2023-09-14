package com.aos.cleanarchitecturemvvm.data.interfaces

import com.aos.cleanarchitecturemvvm.domain.model.Post

interface PostDataSource {
    suspend fun writePost(post: Post): Post
    suspend fun getPosts(): List<Post>
    suspend fun deletePost(post: Post): Int
    suspend fun searchPost(query: String): List<Post>
}
