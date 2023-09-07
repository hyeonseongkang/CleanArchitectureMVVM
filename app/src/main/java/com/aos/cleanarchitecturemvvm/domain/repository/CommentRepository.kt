package com.aos.cleanarchitecturemvvm.domain.repository

import com.aos.cleanarchitecturemvvm.domain.model.Comment

interface CommentRepository {
    suspend fun addComment(comment: Comment): Result<Comment>
    suspend fun getComments(postId: Int): List<Comment>
}