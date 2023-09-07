package com.aos.cleanarchitecturemvvm.domain.usecase

import com.aos.cleanarchitecturemvvm.domain.model.Comment
import com.aos.cleanarchitecturemvvm.domain.repository.CommentRepository

class AddCommentUseCase(private val commentRepository: CommentRepository) {
    suspend fun execute(comment: Comment): Comment {
        return commentRepository.addComment(comment)
    }
}