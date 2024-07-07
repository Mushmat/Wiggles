package com.example.wigglesapp

data class Pet(
    val id: Int,
    val name: String,
    val breed: String,
    val imageUrl: String,
    val gender: String = "",
    val size: String = "",
    val characterstics: String = "",
    val about: String = ""
)
