package com.example.wigglesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val bookmarkedPetDao = db.bookmarkedPetDao()
    private val adoptionApplicationDao = db.adoptionApplicationDao()
    private val auth = FirebaseAuth.getInstance()

    private val _bookmarkedPets = MutableStateFlow<List<BookmarkedPet>>(emptyList())
    val bookmarkedPets: StateFlow<List<BookmarkedPet>> get() = _bookmarkedPets

    private val _suggestedPets = MutableStateFlow<List<Pet>>(emptyList())
    val suggestedPets: StateFlow<List<Pet>> get() = _suggestedPets

    private val _adoptionApplications = MutableStateFlow<List<AdoptionApplicationEntity>>(emptyList())
    val adoptionApplications: StateFlow<List<AdoptionApplicationEntity>> get() = _adoptionApplications

    init {
        viewModelScope.launch {
            auth.currentUser?.let { user ->
                _bookmarkedPets.value = bookmarkedPetDao.getAllBookmarkedPets(user.uid)
                _adoptionApplications.value = adoptionApplicationDao.getAllAdoptionApplications(user.uid)
            }
        }
    }

    fun bookmarkPet(pet: BookmarkedPet) {
        viewModelScope.launch {
            auth.currentUser?.let { user ->
                val bookmarkedPet = pet.copy(userId = user.uid)
                bookmarkedPetDao.insertBookmarkedPet(bookmarkedPet)
                _bookmarkedPets.value = bookmarkedPetDao.getAllBookmarkedPets(user.uid)
            }
        }
    }

    fun removeBookmark(pet: BookmarkedPet) {
        viewModelScope.launch {
            auth.currentUser?.let { user ->
                bookmarkedPetDao.deleteBookmarkedPet(pet.id, user.uid)
                _bookmarkedPets.value = bookmarkedPetDao.getAllBookmarkedPets(user.uid)
            }
        }
    }

    fun setSuggestedPets(pets: List<Pet>) {
        _suggestedPets.value = pets
    }

    fun submitAdoptionApplication(petId: Int, answers: List<String>) {
        viewModelScope.launch {
            auth.currentUser?.let { user ->
                val application = AdoptionApplicationEntity(userId = user.uid, petId = petId, answers = answers)
                adoptionApplicationDao.insertAdoptionApplication(application)
                _adoptionApplications.value = adoptionApplicationDao.getAllAdoptionApplications(user.uid)
            }
        }
    }
}

data class AdoptionApplication(val petId: Int, val answers: List<String>)

