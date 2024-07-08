package com.example.wigglesapp

import kotlinx.coroutines.flow.StateFlow

fun suggestPets(preferences: UserPreferences): List<Pet> {
    return dummyPets.filter { pet ->
        matchesTimeDedication(pet, preferences.timeDedication) &&
        matchesSizePreference(pet, preferences.sizePreference) &&
        matchesChildrenAtHome(pet, preferences.childrenAtHome) &&
        matchesActivityLevel(pet, preferences.activityLevel) &&
        matchesOtherPets(pet, preferences.otherPets) &&
        matchesLivingEnvironment(pet, preferences.livingEnvironment) &&
        matchesReasonForPet(pet, preferences.reasonForPet) &&
        matchesGroomingPreference(pet, preferences.groomingPreference)
    }
}


fun matchesTimeDedication(pet: Pet, timeDedication: String): Boolean {
    return when (timeDedication) {
        "Less than 1 hour" -> pet.characteristics.contains("Couch Potato") || pet.characteristics.contains("Low-energy")
        "1-2 hours" -> pet.characteristics.contains("Moderate-energy")
        "2-4 hours" -> pet.characteristics.contains("High-energy") || pet.characteristics.contains("Playful")
        "More than 4 hours" -> pet.characteristics.contains("Very high-energy") || pet.characteristics.contains("Active")
        else -> true
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

fun matchesActivityLevel(pet: Pet, activityLevel: String): Boolean{
    return when (activityLevel){
        "Very active (exercise daily)" -> pet.characteristics.contains("Active") || pet.characteristics.contains("High-energy")
        "Moderately active (exercise a few times a week" -> pet.characteristics.contains("Moderate-energy")
        "Not very active (occasional exercise)" -> pet.characteristics.contains("Low-energy")
        "Sedentary (rarely exercise)" -> pet.characteristics.contains("Couch Potato")
        else -> true
    }
}

fun matchesOtherPets(pet: Pet, otherPets: String): Boolean {
    return when (otherPets) {
        "Yes, dogs" -> pet.characteristics.contains("Dog-friendly")
        "Yes, cats" -> pet.characteristics.contains("Cat-friendly")
        "Yes, both dogs and cats" -> pet.characteristics.contains("Dog-friendly") && pet.characteristics.contains("Cat-friendly")
        "No other pets" -> true
        else -> true
    }
}

fun matchesLivingEnvironment(pet: Pet, livingEnvironment: String): Boolean {
    return when (livingEnvironment) {
        "Apartment" -> pet.size == "Small" || pet.characteristics.contains("Low-energy")
        "House with a small yard" -> pet.size == "Medium" || pet.characteristics.contains("Moderate-energy")
        "House with a large yard" -> pet.size == "Large" || pet.characteristics.contains("High-energy")
        "Farm/Rural area" -> pet.characteristics.contains("Very high-energy") || pet.characteristics.contains("Active")
        else -> true
    }
}

fun matchesReasonForPet(pet: Pet, reasonForPet: String): Boolean {
    return when (reasonForPet) {
        "Companionship" -> pet.characteristics.contains("Affectionate") || pet.characteristics.contains("Friendly")
        "Protection" -> pet.characteristics.contains("Brave") || pet.characteristics.contains("Loyal")
        "For children" -> pet.characteristics.contains("Playful") || pet.characteristics.contains("Gentle")
        "Therapy/Emotional support" -> pet.characteristics.contains("Calm") || pet.characteristics.contains("Gentle")
        else -> true
    }
}

fun matchesGroomingPreference(pet: Pet, groomingPreference: String): Boolean {
    return when (groomingPreference) {
        "Low maintenance" -> pet.characteristics.contains("Short-haired") || pet.characteristics.contains("Low maintenance")
        "Moderate maintenance" -> pet.characteristics.contains("Moderate maintenance")
        "High maintenance" -> pet.characteristics.contains("High maintenance")
        "No preference" -> true
        else -> true
    }
}