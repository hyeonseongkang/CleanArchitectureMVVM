package com.aos.cleanarchitecturemvvm.data.repository

import com.aos.cleanarchitecturemvvm.domain.model.Post

class PostRepositoryImpl(private val postDataSource: PostDatSource): PostRepository {
    override suspend fun writePost(post: Post): Result<Post> {
        return postDataSource.writePost(post)
    }
}