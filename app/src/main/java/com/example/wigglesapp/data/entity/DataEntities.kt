package com.example.wigglesapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Define a Room entity for user profiles

@Entity("user_profile")
data class UserProfile(
    @PrimaryKey val email: String, // Primary key is the user's email
    val fullName: String, // User's full name
    val dob: String, // Date of birth
    val contactNumber: String, // Contact number
    val address: String // Address
)

// Define a Room entity for bookmarked pets
@Entity("bookmarked_pets")
data class BookmarkedPet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-generated primary key
    val userId: String, // User ID who bookmarked the pet
    val name: String, // Pet's name
    val breed: String, // Pet's breed
    val imageUrl: String, // URL to the pet's image
    val gender: String = "", // Pet's gender (optional, default empty)
    val size: String = "", // Pet's size (optional, default empty)
    val characteristics: String = "", // Pet's characteristics (optional, default empty)
    val about: String = "" // Additional information about the pet (optional, default empty)
)

// Define a Room entity for adoption applications
@Entity(tableName = "adoption_application")
data class AdoptionApplicationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto-generated primary key
    val userId: String, // User ID who submitted the application
    val petId: Int, // ID of the pet being adopted
    val answers: List<String>, // List of answers to adoption questions
    val status: String = "IN PROGRESS", // Status of the adoption application
    val remarks: String = "" // Remarks for the adoption application
)