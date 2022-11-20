package com.pauljuma.karibuapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.pauljuma.karibuapp.data.CartItem
import com.pauljuma.karibuapp.data.FavoriteMealsItem

@Dao
interface FavoriteMealsDao {
    @Query("SELECT* FROM favorite_meal")
    fun getAllFavoriteMeal(): List<FavoriteMealsItem>

    @Query("SELECT* FROM favorite_meal where id=:id")
    fun getFavoriteMeal(id: String): FavoriteMealsItem

    @Upsert
    suspend fun addToFavoriteMeal(favoriteMealsItem: FavoriteMealsItem)

    @Query("SELECT* FROM cart_item")
    fun getAllCartItem(): List<CartItem>

    @Delete
    suspend fun deleteFavorite(favoriteMealsItem: FavoriteMealsItem)

    @Upsert
    suspend fun addToCart(cartItem: CartItem)

    @Query("SELECT* FROM cart_item where id=:id")
    fun getCartItem(id: String): CartItem?

}