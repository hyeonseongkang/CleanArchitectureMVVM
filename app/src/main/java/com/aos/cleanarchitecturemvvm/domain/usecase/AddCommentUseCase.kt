package com.aos.cleanarchitecturemvvm.domain.usecase

import com.aos.cleanarchitecturemvvm.domain.model.Comment

class AddCommentUseCase(private val commentRepository: CommentRepository) {
    suspend fun execute(comment: Comment): Result<Comment> {
        return commentRepository.addComment(comment)
    }
}