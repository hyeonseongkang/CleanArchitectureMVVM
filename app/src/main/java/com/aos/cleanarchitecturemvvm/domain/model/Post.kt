package com.aos.cleanarchitecturemvvm.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val content: String
)
