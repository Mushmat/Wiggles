package com.example.wigglesapp.models

// Data class representing a pet with various attributes

data class Pet(
    val id: Int, // Unique identifier for the pet
    val name: String,  // Pet's name
    val breed: String, // Pet's breed
    val imageUrl: String, // URL to the pet's image
    val gender: String = "", // Pet's gender (optional, default empty)
    val size: String = "", // Pet's size (optional, default empty)
    val characteristics: String = "", // Pet's characteristics (optional, default empty)
    val about: String = ""  // Additional information about the pet (optional, default empty)
)

// Data class representing user preferences for adopting a pet

data class UserPreferences(
    val timeDedication: String, // User's available time for pet care
    val sizePreference: String, // Preferred size of the pet
    val childrenAtHome: String, // Information on children at home
    val activityLevel: String, // Preferred activity level of the pet
    val otherPets: String, // Information on other pets at home
    val livingEnvironment: String, // Type of living environment (e.g., apartment, house)
    val reasonForPet: String, // User's reason for wanting a pet
    val groomingPreference: String // Preferred grooming level for the pet
)
