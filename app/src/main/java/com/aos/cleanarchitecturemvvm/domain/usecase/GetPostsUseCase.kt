package com.aos.cleanarchitecturemvvm.domain.usecase

import com.aos.cleanarchitecturemvvm.domain.model.Post
import com.aos.cleanarchitecturemvvm.domain.repository.PostRepository

class GetPostsUseCase (private val postRepository: PostRepository) {
    suspend fun execute(): List<Post> {
        return postRepository.getPosts()
    }
}