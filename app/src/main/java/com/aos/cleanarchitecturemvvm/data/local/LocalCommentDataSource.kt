package com.aos.cleanarchitecturemvvm.data.local

import com.aos.cleanarchitecturemvvm.data.interfaces.CommentDataSource
import com.aos.cleanarchitecturemvvm.domain.model.Comment

class LocalCommentDataSource : CommentDataSource {
    private val comments = mutableListOf<Comment>()

    override suspend fun addComment(comment: Comment): Comment {
        comments.add(comment)
        return comment
    }

    override suspend fun getComments(postId: Int): List<Comment> {
        return comments.filter { it.postId == postId }
    }
}
