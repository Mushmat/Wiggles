package com.example.wigglesapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_pets")
data class PetEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val breed: String,
    val imageUrl: String,
    val gender: String,
    val size: String,
    val characteristics: String,
    val about: String
)

fun Pet.toEntity() = PetEntity(
    id = id,
    name = name,
    breed = breed,
    imageUrl = imageUrl,
    gender = gender,
    size = size,
    characteristics = characteristics,
    about = about
)

fun PetEntity.toPet() = Pet(
    id = id,
    name = name,
    breed = breed,
    imageUrl = imageUrl,
    gender = gender,
    size = size,
    characteristics = characteristics,
    about = about
)

