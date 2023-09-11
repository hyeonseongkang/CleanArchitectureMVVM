package com.aos.cleanarchitecturemvvm.data.local

import android.content.Context
import com.aos.cleanarchitecturemvvm.data.`interface`.PostDataSource
import com.aos.cleanarchitecturemvvm.data.local.db.AppDatabase
import com.aos.cleanarchitecturemvvm.data.local.db.PostDao
import com.aos.cleanarchitecturemvvm.domain.model.Post

class LocalPostDataSource(private val postDao: PostDao) : PostDataSource {

    override suspend fun writePost(post: Post): Post {
        postDao.insertPost(post)
        return post
    }

    override suspend fun getPosts(): List<Post> {
        return postDao.getAllPosts()
    }

    // deletePost 메서드 추가 (원래 문제에서 제안된 부분)
    override suspend fun deletePost(post: Post): Int {
        return postDao.deletePost(post)
    }

    override suspend fun searchPost(query: String): List<Post> {
        return postDao.searchPosts(query)
    }
}
