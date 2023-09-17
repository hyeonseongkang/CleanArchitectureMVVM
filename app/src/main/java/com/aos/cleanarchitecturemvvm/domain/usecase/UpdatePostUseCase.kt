package com.aos.cleanarchitecturemvvm.domain.usecase

import com.aos.cleanarchitecturemvvm.domain.model.Post
import com.aos.cleanarchitecturemvvm.domain.repository.PostRepository

class UpdatePostUseCase(private val postRepository: PostRepository) {

    suspend fun execute(post: Post): Post {
        return postRepository.updatePost(post)
    }
}