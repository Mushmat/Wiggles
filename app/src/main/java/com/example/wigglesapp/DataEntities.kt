package com.example.wigglesapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_profile")
data class UserProfile(
    @PrimaryKey val email: String,
    val fullName: String,
    val dob: String,
    val contactNumber: String,
    val address: String
)

@Entity("bookmarked_pets")
data class BookmarkedPet(
    @PrimaryKey val id: Int,
    val name: String,
    val breed: String,
    val imageUrl: String,
    val gender: String = "",
    val size: String = "",
    val characteristics: String = "",
    val about: String = ""
)

@Entity(tableName = "adoption_application")
data class AdoptionApplicationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val petId: Int,
    val answers: List<String>
)