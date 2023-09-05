package com.aos.cleanarchitecturemvvm.data.`interface`

import com.aos.cleanarchitecturemvvm.domain.model.Post

interface PostDataSource {
    suspend fun writePost(post: Post): Post
}