package com.example.wigglesapp

data class Pet(
    val id: Int,
    val name: String,
    val breed: String,
    val imageUrl: String,
    val gender: String = "",
    val size: String = "",
    val characteristics: String = "",
    val about: String = ""
)

data class UserPreference(
    val timeDedication: String,
    val sizePreference: String,
    val childrenAtHome: String,
    val activityLevel: String,
    val otherPets: String,
    val livingEnvironment: String,
    val reasonForPet: String,
    val groomingPreference: String
)