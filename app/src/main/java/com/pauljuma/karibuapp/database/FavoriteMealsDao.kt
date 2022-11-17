package com.pauljuma.karibuapp.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pauljuma.karibuapp.data.FavoriteMealsItem

@Dao
interface FavoriteMealsDao {
    @Query("SELECT* FROM favorite_meal")
    fun getAllFavoriteMeal(): List<FavoriteMealsItem>

    @Upsert
    fun addToFavoriteMeal(favoriteMealsItem: FavoriteMealsItem)
}