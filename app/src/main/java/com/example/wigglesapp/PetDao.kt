package com.example.wigglesapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pet: PetEntity)

    @Query("DELETE FROM bookmarked_pets where id = :petId")
    suspend fun delete(petId: Int)

    @Query("SELECT * FROM bookmarked_pets")
    fun getBookmarkedPets(): Flow<List<PetEntity>>

}