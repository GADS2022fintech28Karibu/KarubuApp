package com.pauljuma.karibuapp.repository

import com.pauljuma.karibuapp.data.CartItem
import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.data.FeaturedPartnersItem
import com.pauljuma.karibuapp.database.AppDatabase
import com.pauljuma.karibuapp.network.KaribuApiInstance

class KaribuRepository(val db: AppDatabase) {

    suspend fun getItems(): List<FeaturedPartnersItem> {

        val item = KaribuApiInstance.api.getFeaturedPartners()
        item.forEach {
            db.getFeaturedPartnerDao().addToFeaturedPartnerItems(it)
        }
        return db.getFeaturedPartnerDao().getAllFeaturedPartnerItems()
    }

    suspend fun getFavoriteItem(): List<FavoriteMealsItem> {
        val response = KaribuApiInstance.api.getFavoriteItems()
        //store in local database
        response.forEach{
            db.getFavoriteMealDao().addToFavoriteMeal(it)
        }
        return db.getFavoriteMealDao().getAllFavoriteMeal()
    }

    suspend fun addFavoriteMeal(favoriteMealsItem: FavoriteMealsItem) =
        db.getFavoriteMealDao().addToFavoriteMeal(favoriteMealsItem)

    suspend fun delete(cartItem: CartItem) =
        db.getFavoriteMealDao().deleteFavorite(cartItem)
    suspend fun addCartItem(cartItem: CartItem){
        val dbItem = db.getFavoriteMealDao().getCartItem(cartItem.id)
        if (dbItem == null){
            db.getFavoriteMealDao().addToCart(cartItem)
        }else{
            dbItem.quantity += 1
            db.getFavoriteMealDao().addToCart(dbItem)
        }
    }

    fun getCartItem(): List<CartItem> = db.getFavoriteMealDao().getAllCartItem()
    suspend fun getProduct(): List<FavoriteMealsItem> = db.getFavoriteMealDao().getAllFavoriteMeal()

}