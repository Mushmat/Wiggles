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

// Define the Room database for the application

@Database(
    entities = [UserProfile::class, BookmarkedPet::class, AdoptionApplicationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class) // Converters for custom types
abstract class AppDatabase : RoomDatabase(){
    // Abstract methods to get DAO objects
    abstract fun userProfileDao(): UserProfileDao
    abstract fun bookmarkedPetDao(): BookmarkedPetDao
    abstract fun adoptionApplicationDao(): AdoptionApplicationDao

    companion object {
        // Volatile variable to ensure atomic access to the variable
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Function to get the singleton instance of the database
        fun getDatabase(context: Context): AppDatabase {
            // If the INSTANCE is not null, return it; if it is, create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, // Application context
                    AppDatabase::class.java, // Database class
                    "app_database" // Database name
                ).build()
                INSTANCE = instance // Assign the instance to the INSTANCE variable
                instance // Return the instance
            }
        }
    }
}

// Class to handle type conversions for Room database

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        // Convert a list of strings to a single comma-separated string
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        // Convert a comma-separated string back to a list of strings
        return value.split(",")
    }
}