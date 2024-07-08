package com.example.wigglesapp

fun suggestPets(preferences: UserPreferences): List<Pet> {
    val petId = when {
        preferences.timeDedication == "Less than 1 hour" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "No children" -> 2
        preferences.timeDedication == "Less than 1 hour" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "No children" -> 3
        preferences.timeDedication == "1-2 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "No children" -> 5
        preferences.timeDedication == "1-2 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "No children" -> 6
        preferences.timeDedication == "2-4 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "No children" -> 7
        preferences.timeDedication == "2-4 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "No children" -> 1
        preferences.timeDedication == "More than 4 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "No children" -> 8
        preferences.timeDedication == "More than 4 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "No children" -> 21
        preferences.timeDedication == "Less than 1 hour" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, under 5 years old" -> 21
        preferences.timeDedication == "Less than 1 hour" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, under 5 years old" -> 24
        preferences.timeDedication == "1-2 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, under 5 years old" -> 25
        preferences.timeDedication == "1-2 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, under 5 years old" -> 27
        preferences.timeDedication == "2-4 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, under 5 years old" -> 24
        preferences.timeDedication == "2-4 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, under 5 years old" -> 30
        preferences.timeDedication == "More than 4 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, under 5 years old" -> 2
        preferences.timeDedication == "More than 4 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, under 5 years old" -> 3

        preferences.timeDedication == "Less than 1 hour" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, 5-10 years old" -> 5
        preferences.timeDedication == "Less than 1 hour" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, 5-10 years old" -> 6
        preferences.timeDedication == "1-2 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, 5-10 years old" -> 8
        preferences.timeDedication == "1-2 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, 5-10 years old" -> 9
        preferences.timeDedication == "2-4 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, 5-10 years old" -> 21
        preferences.timeDedication == "2-4 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, 5-10 years old" -> 22
        preferences.timeDedication == "More than 4 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, 5-10 years old" -> 24
        preferences.timeDedication == "More than 4 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, 5-10 years old" -> 25

        preferences.timeDedication == "Less than 1 hour" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, older than 10 years old" -> 25
        preferences.timeDedication == "Less than 1 hour" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, older than 10 years old" -> 28
        preferences.timeDedication == "1-2 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, older than 10 years old" -> 7
        preferences.timeDedication == "1-2 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, older than 10 years old" -> 1
        preferences.timeDedication == "2-4 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, older than 10 years old" -> 3
        preferences.timeDedication == "2-4 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, older than 10 years old" -> 4
        preferences.timeDedication == "More than 4 hours" && preferences.sizePreference == "Small" && preferences.childrenAtHome == "Yes, older than 10 years old" -> 5
        preferences.timeDedication == "More than 4 hours" && preferences.sizePreference == "Medium" && preferences.childrenAtHome == "Yes, older than 10 years old" -> 7

        else -> 1
    }

    return dummyPets.filter { it.id == petId }
}
