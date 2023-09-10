package com.aos.cleanarchitecturemvvm.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aos.cleanarchitecturemvvm.domain.model.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post): Long

    @Query("SELECT * FROM post")
    suspend fun getAllPosts(): List<Post>

    @Delete
    suspend fun deletePost(post: Post): Int
}
