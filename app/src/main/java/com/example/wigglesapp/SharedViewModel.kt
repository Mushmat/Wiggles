// SharedViewModel.kt
package com.example.wigglesapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel: ViewModel() {
    private val _bookmarkedPets = MutableStateFlow<List<Pet>>(emptyList())
    val bookmarkedPets: StateFlow<List<Pet>> get() = _bookmarkedPets

    private val _suggestedPets = MutableStateFlow<List<Pet>>(emptyList())
    val suggestedPets: StateFlow<List<Pet>> get() = _suggestedPets

    private val _adoptionApplications = MutableStateFlow<List<AdoptionApplication>>(emptyList())
    val adoptionApplications: StateFlow<List<AdoptionApplication>> get() = _adoptionApplications


    fun bookmarkPet(pet: Pet) {
        _bookmarkedPets.value += pet
    }

    fun removeBookmark(pet: Pet) {
        _bookmarkedPets.value -= pet
    }

    fun setSuggestedPets(pets: List<Pet>) {
        _suggestedPets.value = pets
    }

    fun submitAdoptionApplication(petId: Int, answers: List<String>) {
        val application = AdoptionApplication(petId, answers)
        _adoptionApplications.value = _adoptionApplications.value.toMutableList().also { it.add(application) }
    }
}

data class AdoptionApplication(val petId: Int, val answers: List<String>)

