package com.example.wigglesapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfile)

    @Query("SELECT * FROM user_profile WHERE email = :email LIMIT 1")
    suspend fun getUserProfile(email:String): UserProfile?
}

@Dao
interface BookmarkedPetDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkedPet(bookmarkedPet: BookmarkedPet)

    @Query("SELECT * FROM bookmarked_pets")
    suspend fun getAllBookmarkedPets(): List<BookmarkedPet>

    @Query("DELETE FROM bookmarked_pets WHERE id = :id")
    suspend fun deleteBookmarkedPet(id: Int)
}

@Dao
interface AdoptionApplicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdoptionApplication(application: AdoptionApplicationEntity)

    @Query("SELECT * FROM adoption_application")
    suspend fun getAllAdoptionApplications(): List<AdoptionApplicationEntity>
}