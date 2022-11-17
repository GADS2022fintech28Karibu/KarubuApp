package com.pauljuma.karibuapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorite_meal")
data class FavoriteMealsItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val family: String,
    val firstName: String,
    val fullName: String,
    val image: String,
    val imageUrl: String,
    val lastName: String,
    val title: String
)