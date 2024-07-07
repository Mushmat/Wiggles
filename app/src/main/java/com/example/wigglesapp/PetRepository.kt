package com.example.wigglesapp

import kotlinx.coroutines.flow.Flow

class PetRepository(private val petDao: PetDao) {

    val bookmarkedPets: Flow<List<PetEntity>> = petDao.getBookmarkedPets()

    suspend fun bookmarkPet(pet: PetEntity){
        petDao.insert(pet)
    }
    suspend fun removeBookmark(petId: Int){
        petDao.delete(petId)
    }

}