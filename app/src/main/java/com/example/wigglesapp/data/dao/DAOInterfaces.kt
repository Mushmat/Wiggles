package com.example.wigglesapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wigglesapp.data.entity.AdoptionApplicationEntity
import com.example.wigglesapp.data.entity.BookmarkedPet
import com.example.wigglesapp.data.entity.UserProfile

// Data Access Object (DAO) for user profile operations
@Dao
interface UserProfileDao {
    // Insert a new user profile into the database
    // If a conflict occurs, replace the existing profile
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(userProfile: UserProfile)

    // Query to get a user profile based on the email
    // Returns the user profile if found, otherwise null
    @Query("SELECT * FROM user_profile WHERE email = :email LIMIT 1")
    suspend fun getUserProfile(email:String): UserProfile?
}

// Data Access Object (DAO) for bookmarked pets operations

@Dao
interface BookmarkedPetDao {
    // Query to get all bookmarked pets for a specific user
    // Returns a list of bookmarked pets
    @Query("SELECT * FROM bookmarked_pets WHERE userId = :userId")
    suspend fun getAllBookmarkedPets(userId: String): List<BookmarkedPet>

    // Insert a new bookmarked pet into the database
    // If a conflict occurs, replace the existing bookmarked pet
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkedPet(bookmarkedPet: BookmarkedPet)

    // Delete a specific bookmarked pet based on its id and userId
    @Query("DELETE FROM bookmarked_pets WHERE id = :id AND userId = :userId")
    suspend fun deleteBookmarkedPet(id: Int, userId: String)
}

// Data Access Object (DAO) for adoption application operations
@Dao
interface AdoptionApplicationDao {
    // Query to get all adoption applications for a specific user
    // Returns a list of adoption applications
    @Query("SELECT * FROM adoption_application WHERE userId = :userId")
    suspend fun getAllAdoptionApplications(userId: String): List<AdoptionApplicationEntity>

    // Insert a new adoption application into the database
    // If a conflict occurs, replace the existing adoption application
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdoptionApplication(adoptionApplicationEntity: AdoptionApplicationEntity)

    // Delete a specific adoption application based on its id and userId
    @Query("DELETE FROM adoption_application WHERE id = :id AND userId = :userId")
    suspend fun deleteAdoptionApplication(id: Int, userId: String)
}
