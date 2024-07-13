// SharedViewModel.kt
package com.example.wigglesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val bookmarkedPetDao = db.bookmarkedPetDao()
    private val adoptionApplicationDao = db.adoptionApplicationDao()

    private val _bookmarkedPets = MutableStateFlow<List<BookmarkedPet>>(emptyList())
    val bookmarkedPets: StateFlow<List<BookmarkedPet>> get() = _bookmarkedPets

    private val _suggestedPets = MutableStateFlow<List<Pet>>(emptyList())
    val suggestedPets: StateFlow<List<Pet>> get() = _suggestedPets

    private val _adoptionApplications = MutableStateFlow<List<AdoptionApplicationEntity>>(emptyList())
    val adoptionApplications: StateFlow<List<AdoptionApplicationEntity>> get() = _adoptionApplications

    init {
        viewModelScope.launch {
            _bookmarkedPets.value = bookmarkedPetDao.getAllBookmarkedPets()
            _adoptionApplications.value = adoptionApplicationDao.getAllAdoptionApplications()
        }
    }

    fun bookmarkPet(pet: BookmarkedPet) {
        viewModelScope.launch {
            bookmarkedPetDao.insertBookmarkedPet(pet)
            _bookmarkedPets.value = bookmarkedPetDao.getAllBookmarkedPets()
        }
    }

    fun removeBookmark(pet: BookmarkedPet) {
        viewModelScope.launch {
            bookmarkedPetDao.deleteBookmarkedPet(pet.id)
            _bookmarkedPets.value = bookmarkedPetDao.getAllBookmarkedPets()
        }
    }

    fun setSuggestedPets(pets: List<Pet>) {
        _suggestedPets.value = pets
    }

    fun submitAdoptionApplication(petId: Int, answers: List<String>) {
        viewModelScope.launch {
            val application = AdoptionApplicationEntity(petId = petId, answers = answers)
            adoptionApplicationDao.insertAdoptionApplication(application)
            _adoptionApplications.value = adoptionApplicationDao.getAllAdoptionApplications()
        }
    }
}

data class AdoptionApplication(val petId: Int, val answers: List<String>)

