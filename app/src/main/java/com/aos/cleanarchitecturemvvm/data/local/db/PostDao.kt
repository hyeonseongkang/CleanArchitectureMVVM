package com.aos.cleanarchitecturemvvm.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aos.cleanarchitecturemvvm.domain.model.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post): Long

    @Query("SELECT * FROM post")
    suspend fun getAllPosts(): List<Post>

    @Delete
    suspend fun deletePost(post: Post): Int

    @Query("SELECT * FROM post WHERE title LIKE :query OR content LIKE :query")
    suspend fun searchPosts(query: String): List<Post>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePost(post: Post): Post
}
