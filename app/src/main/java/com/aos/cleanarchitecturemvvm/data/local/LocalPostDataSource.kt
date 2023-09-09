package com.aos.cleanarchitecturemvvm.data.local

import android.content.Context
import com.aos.cleanarchitecturemvvm.data.`interface`.PostDataSource
import com.aos.cleanarchitecturemvvm.data.local.db.AppDatabase
import com.aos.cleanarchitecturemvvm.domain.model.Post

class LocalPostDataSource(context: Context) : PostDataSource {
    private val postDao = AppDatabase.getDatabase(context).postDao()

    override suspend fun writePost(post: Post): Post {
        postDao.insertPost(post)
        return post
    }

    override suspend fun getPosts(): List<Post> {
        return postDao.getAllPosts()
    }
}