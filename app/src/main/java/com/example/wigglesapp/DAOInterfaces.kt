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
interface BookmarkedPetDao {
    @Query("SELECT * FROM bookmarked_pets WHERE userId = :userId")
    suspend fun getAllBookmarkedPets(userId: String): List<BookmarkedPet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkedPet(bookmarkedPet: BookmarkedPet)

    @Query("DELETE FROM bookmarked_pets WHERE id = :id AND userId = :userId")
    suspend fun deleteBookmarkedPet(id: Int, userId: String)
}


@Dao
interface AdoptionApplicationDao {
    @Query("SELECT * FROM adoption_application WHERE userId = :userId")
    suspend fun getAllAdoptionApplications(userId: String): List<AdoptionApplicationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdoptionApplication(adoptionApplicationEntity: AdoptionApplicationEntity)

    @Query("DELETE FROM adoption_application WHERE id = :id AND userId = :userId")
    suspend fun deleteAdoptionApplication(id: Int, userId: String)
}
