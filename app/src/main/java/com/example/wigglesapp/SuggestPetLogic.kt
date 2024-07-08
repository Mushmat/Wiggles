package com.example.wigglesapp

fun suggestPets(preferences: UserPreferences): List<Pet> {
    return dummyPets.filter { pet ->


    }
}

fun matchesSizePreference(pet: Pet, sizePreference: String): Boolean {
    return sizePreference == "No preference" || pet.size == sizePreference
}

fun matchesChildrenAtHome(pet: Pet, childrenAtHome: String): Boolean {
    return when (childrenAtHome) {
        "Yes, under 5 years old" -> pet.characteristics.contains("Gentle") || pet.characteristics.contains("Patient")
        "Yes, 5-10 years old" -> pet.characteristics.contains("Playful")
        "Yes, older than 10 years old" -> pet.characteristics.contains("Friendly") || pet.characteristics.contains("Affectionate")
        "No children" -> true
        else -> true
    }
}