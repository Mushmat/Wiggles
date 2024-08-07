package com.example.wigglesapp.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.wigglesapp.data.entity.AdoptionApplicationEntity
import com.example.wigglesapp.data.entity.BookmarkedPet
import com.example.wigglesapp.data.entity.UserProfile

@Database(
    entities = [UserProfile::class, BookmarkedPet::class, AdoptionApplicationEntity::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun bookmarkedPetDao(): BookmarkedPetDao
    abstract fun adoptionApplicationDao(): AdoptionApplicationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Enable destructive migration
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",")
    }
}
