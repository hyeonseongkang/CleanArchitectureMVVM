package com.aos.cleanarchitecturemvvm.domain.model

data class Comment(val id: Int, val postId: Int, val author: String, val content: String)
