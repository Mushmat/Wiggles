package com.example.wigglesapp

fun suggestPets(preferences: UserPreferences): List<Pet> {
    return dummyPets.filter { pet ->


    }
}

fun matchesSizePreference(pet: Pet, sizePreference: String): Boolean {
    return sizePreference == "No preference" || pet.size == sizePreference
}

