package com.aos.cleanarchitecturemvvm.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.aos.cleanarchitecturemvvm.domain.model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String): List<String> = value.split(";")

    @TypeConverter
    @JvmStatic
    fun fromList(list: List<String>): String = list.joinToString(";")
}
