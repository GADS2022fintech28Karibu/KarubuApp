package com.pauljuma.karibuapp.repository

import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.data.FeaturedPartnersItem
import com.pauljuma.karibuapp.database.AppDatabase
import com.pauljuma.karibuapp.network.KaribuApiInstance

class KaribuRepository(val db: AppDatabase) {

    suspend fun getItems(): List<FeaturedPartnersItem>{

        val item = KaribuApiInstance.api.getFeaturedPartners()
        item.forEach {
            db.getFeaturedPartnerDao().addToFeaturedPartnerItems(it)
        }
        return db.getFeaturedPartnerDao().getAllFeaturedPartnerItems()
    }

   suspend fun getFavoriteMealsItem(): List<FavoriteMealsItem> {

        val item = KaribuApiInstance.api.getFavoriteItems()
        item.forEach {
            db.getFavoriteMealDao().addToFavoriteMeal(it)
        }
        return db.getFavoriteMealDao().getAllFavoriteMeal()
    }
}