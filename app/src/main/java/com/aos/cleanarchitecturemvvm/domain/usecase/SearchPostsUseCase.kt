package com.aos.cleanarchitecturemvvm.domain.usecase

import com.aos.cleanarchitecturemvvm.domain.model.Post
import com.aos.cleanarchitecturemvvm.domain.repository.PostRepository

class SearchPostsUseCase(private val postRepository: PostRepository) {
    suspend fun execute(query: String): List<Post> {
        return postRepository.searchPost(query)
    }
}