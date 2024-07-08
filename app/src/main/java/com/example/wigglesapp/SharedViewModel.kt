package com.example.wigglesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel: ViewModel() {
    private val _bookmarkedPets = MutableStateFlow<List<Pet>>(emptyList())
    val bookmarkedPets: StateFlow<List<Pet>> = _bookmarkedPets.asStateFlow()

    private val _suggestedPets = MutableStateFlow<List<Pet>>(emptyList())
    var suggestedPets: StateFlow<List<Pet>> = _suggestedPets.asStateFlow()

    fun bookmarkPet(pet: Pet) {
        _bookmarkedPets.value += pet
    }

    fun removeBookmark(pet: Pet) {
        _bookmarkedPets.value -= pet
    }

    fun setSuggestedPets(pets: List<Pet>) {
        _suggestedPets.value = pets
    }
}