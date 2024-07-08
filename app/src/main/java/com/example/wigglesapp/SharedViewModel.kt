package com.example.wigglesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel: ViewModel() {
    private val _bookmarkedPets = MutableStateFlow<List<Pet>>(emptyList())
    val bookmarkedPets: StateFlow<List<Pet>> = _bookmarkedPets

    fun bookmarkPet(pet:Pet){
        if(_bookmarkedPets.value.contains(pet).not()) {
            _bookmarkedPets.value += pet
        }
    }

    fun removeBookmark(pet:Pet){
        _bookmarkedPets.value -= pet
    }
}