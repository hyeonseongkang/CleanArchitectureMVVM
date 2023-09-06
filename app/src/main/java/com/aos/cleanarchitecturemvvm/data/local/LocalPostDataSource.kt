package com.aos.cleanarchitecturemvvm.data.local

import com.aos.cleanarchitecturemvvm.data.`interface`.PostDataSource
import com.aos.cleanarchitecturemvvm.domain.model.Post

class LocalPostDataSource: PostDataSource {
    private val posts = mutableListOf<Post>()

    override suspend fun writePost(post: Post): Post {
        return try {
            posts.add(post)
            post
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPosts(): List<Post> {
        return posts
    }
}