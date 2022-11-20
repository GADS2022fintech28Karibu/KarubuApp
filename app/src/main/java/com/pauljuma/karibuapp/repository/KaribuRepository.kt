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
        return KaribuApiInstance.api.getFavoriteItems()
    }

    suspend fun upsert(favoriteMealsItem: FavoriteMealsItem) =
        db.getFavoriteMealDao().addToFavoriteMeal(favoriteMealsItem)

    suspend fun delete(favoriteMealsItem: FavoriteMealsItem) =
        db.getFavoriteMealDao().deleteFavorite(favoriteMealsItem)

    fun getAllFavorite() = db.getFavoriteMealDao().getAllFavoriteMeal()

    suspend fun addCartItem(cartItem: CartItem){
        val dbItem = db.getFavoriteMealDao().getCartItem(cartItem.id)
        if (dbItem == null){
            db.getFavoriteMealDao().addToCart(cartItem)
        }else{
            dbItem.quantity += 1
            db.getFavoriteMealDao().addToCart(dbItem)
        }
    }

    suspend fun getCartItem(): List<CartItem> = db.getFavoriteMealDao().getAllCartItem()
    suspend fun getProduct(): List<FavoriteMealsItem> = db.getFavoriteMealDao().getAllFavoriteMeal()

}