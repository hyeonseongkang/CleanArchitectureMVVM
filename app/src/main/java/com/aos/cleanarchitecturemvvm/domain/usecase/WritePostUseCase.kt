package com.aos.cleanarchitecturemvvm.domain.usecase

import com.aos.cleanarchitecturemvvm.domain.repository.PostRepository
import com.aos.cleanarchitecturemvvm.domain.model.Post

class WritePostUseCase (private val postRepository: PostRepository){
    suspend fun execute(post: Post) = postRepository.writePost(post)
}