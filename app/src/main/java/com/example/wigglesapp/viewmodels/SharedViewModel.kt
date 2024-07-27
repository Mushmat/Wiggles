package com.example.wigglesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.wigglesapp.models.Pet
import com.example.wigglesapp.data.dao.AppDatabase
import com.example.wigglesapp.data.entity.AdoptionApplicationEntity
import com.example.wigglesapp.data.entity.BookmarkedPet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    // Get instances of the database and DAO
    private val db = AppDatabase.getDatabase(application)
    private val bookmarkedPetDao = db.bookmarkedPetDao()
    private val adoptionApplicationDao = db.adoptionApplicationDao()
    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    // MutableStateFlow to hold the list of bookmarked pets
    private val _bookmarkedPets = MutableStateFlow<List<BookmarkedPet>>(emptyList())
    val bookmarkedPets: StateFlow<List<BookmarkedPet>> get() = _bookmarkedPets

    // MutableStateFlow to hold the list of suggested pets
    private val _suggestedPets = MutableStateFlow<List<Pet>>(emptyList())
    val suggestedPets: StateFlow<List<Pet>> get() = _suggestedPets

    // MutableStateFlow to hold the list of adoption applications
    private val _adoptionApplications = MutableStateFlow<List<AdoptionApplicationEntity>>(emptyList())
    val adoptionApplications: StateFlow<List<AdoptionApplicationEntity>> get() = _adoptionApplications

    // Initialize the ViewModel by fetching the initial data for the current user
    init {
        viewModelScope.launch {
            auth.currentUser?.let { user ->
                _bookmarkedPets.value = bookmarkedPetDao.getAllBookmarkedPets(user.uid)
                _adoptionApplications.value = adoptionApplicationDao.getAllAdoptionApplications(user.uid)
            }
        }
        fetchAdoptionApplications()
    }

    // Function to bookmark a pet
    fun bookmarkPet(pet: BookmarkedPet) {
        viewModelScope.launch {
            auth.currentUser?.let { user ->
                val bookmarkedPet = pet.copy(userId = user.uid)
                bookmarkedPetDao.insertBookmarkedPet(bookmarkedPet)
                _bookmarkedPets.value = bookmarkedPetDao.getAllBookmarkedPets(user.uid)
            }
        }
    }

    // Function to remove a bookmarked pet
    fun removeBookmark(pet: BookmarkedPet) {
        viewModelScope.launch {
            auth.currentUser?.let { user ->
                bookmarkedPetDao.deleteBookmarkedPet(pet.id, user.uid)
                _bookmarkedPets.value = bookmarkedPetDao.getAllBookmarkedPets(user.uid)
            }
        }
    }

    // Function to set the list of suggested pets
    fun setSuggestedPets(pets: List<Pet>) {
        _suggestedPets.value = pets
    }

    // Function to submit an adoption application
    fun submitAdoptionApplication(petId: Int, answers: List<String>) {
        viewModelScope.launch {
            auth.currentUser?.let { user ->
                val application = AdoptionApplicationEntity(
                    userId = user.uid, petId = petId, answers = answers,
                    status = "IN PROGRESS", remarks = "")
                adoptionApplicationDao.insertAdoptionApplication(application)
                _adoptionApplications.value = adoptionApplicationDao.getAllAdoptionApplications(user.uid)

                //TO FIRESTORE
                val applicationData = mapOf(
                    "userId" to user.uid,
                    "petId" to petId,
                    "answers" to answers,
                    "status" to "IN PROGRESS",
                    "remarks" to ""
                )
                firestore.collection("adoptionRequests").add(applicationData)
            }
        }
    }

    //Function to fetch adoption applications from FireStore

    private fun fetchAdoptionApplications(){
        auth.currentUser?.let{ user ->
            firestore.collection("adoptionRequests")
                .whereEqualTo("userId", user.uid)
                .addSnapshotListener{ snapshots, e ->
                    if(e != null || snapshots == null){
                        return@addSnapshotListener
                    }
                    val applications = snapshots.documents.mapNotNull { doc ->
                        doc.toObject(AdoptionApplicationEntity::class.java)?.copy(id = doc.id)
                    }
                    _adoptionApplications.value = applications

                }
        }
    }

    // Function to update the status of an adoption application
    fun updateAdoptionApplicationStatus(applicationId: String, status: String, remarks: String) {
        firestore.collection("adoptionRequests").document(applicationId)
            .update(mapOf("status" to status, "remarks" to remarks))
    }


}

// Data class to represent an adoption application
data class AdoptionApplication(
    val petId: Int,
    val answers: List<String>
)

