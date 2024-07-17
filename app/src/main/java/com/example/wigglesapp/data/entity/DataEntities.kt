package com.example.wigglesapp.data.entity

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
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
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
    val userId: String,
    val petId: Int,
    val answers: List<String>
)