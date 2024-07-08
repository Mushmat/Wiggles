package com.example.wigglesapp

import androidx.lifecycle.ViewModel
import com.example.wigglesapp.Pet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {
    private val _bookmarkedPets = MutableStateFlow<List<Pet>>(emptyList())
    val bookmarkedPets: StateFlow<List<Pet>> get() = _bookmarkedPets

    private val _suggestedPets = MutableStateFlow<List<Pet>>(emptyList())
    val suggestedPets: StateFlow<List<Pet>> get() = _suggestedPets

    fun bookmarkPet(pet: Pet) {
        _bookmarkedPets.value += pet
    }

    fun removeBookmark(pet: Pet) {
        _bookmarkedPets.value -= pet
    }

    fun setSuggestedPets(pets: List<Pet>) {
        _suggestedPets.value = pets
        // Debug log
        println("Suggested Pets: ${pets.map { it.name }}")
    }
}
