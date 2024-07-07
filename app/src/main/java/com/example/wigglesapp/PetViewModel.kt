package com.example.wigglesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PetViewModel(application: Application): AndroidViewModel(application) {

    private val repository: PetRepository
    val bookmarkedPets: Flow<List<PetEntity>>

    init {
        val petDao = PetDatabase.getDatabase(application).petDao()
        repository = PetRepository(petDao)
        bookmarkedPets = repository.bookmarkedPets
    }

    fun bookmarkPet(pet:Pet) = viewModelScope.launch {
        repository.bookmarkPet(pet.toEntity())
    }
    fun removeBookmark(petId: Int) = viewModelScope.launch {
        repository.removeBookmark(petId)
    }



}