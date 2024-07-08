package com.example.wigglesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel: ViewModel() {
    private val _bookmarkedPets = MutableStateFlow<List<Pet>>(emptyList())
    val bookmarkedPets: StateFlow<List<Pet>> = _bookmarkedPets

    private val _suggestedPets = MutableStateFlow<List<Pet>>(emptyList())
    var suggestedPets: StateFlow<List<Pet>> = _suggestedPets

    fun bookmarkPet(pet:Pet){
        if(_bookmarkedPets.value.contains(pet).not()) {
            _bookmarkedPets.value += pet
        }
    }
    fun removeBookmark(pet:Pet){
        _bookmarkedPets.value -= pet
    }
    fun setSuggestedPets(pets: List<Pet>) {
        _suggestedPets.value = pets
    }
}