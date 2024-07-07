package com.example.wigglesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PetViewModel @Inject constructor(
    private val repository: PetRepository
): ViewModel() {

    val bookmarkedPets: Flow<List<PetEntity>> = repository.bookmarkedPets


    fun bookmarkPet(pet:Pet) = viewModelScope.launch {
        repository.bookmarkPet(pet.toEntity())
    }
    fun removeBookmark(petId: Int) = viewModelScope.launch {
        repository.removeBookmark(petId)
    }



}