package com.aos.cleanarchitecturemvvm.domain.usecase

import com.aos.cleanarchitecturemvvm.domain.model.Comment
import com.aos.cleanarchitecturemvvm.domain.repository.CommentRepository

class GetCommentsUseCase(private val commentRepository: CommentRepository) {
    suspend fun execute(postId: Int): List<Comment> {
        return commentRepository.getComments(postId)
    }
}