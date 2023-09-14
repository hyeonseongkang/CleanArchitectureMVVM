package com.aos.cleanarchitecturemvvm.data.interfaces

import com.aos.cleanarchitecturemvvm.domain.model.Comment

interface CommentDataSource {
    suspend fun addComment(comment: Comment): Comment
    suspend fun getComments(postId: Int): List<Comment>
}