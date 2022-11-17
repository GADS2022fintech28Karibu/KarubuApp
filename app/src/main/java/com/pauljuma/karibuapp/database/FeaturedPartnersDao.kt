package com.pauljuma.karibuapp.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.pauljuma.karibuapp.data.FeaturedPartnersItem

@Dao
interface FeaturedPartnersDao {
    @Query("SELECT * FROM partner_name")
    fun getAllFeaturedPartnerItems(): List<FeaturedPartnersItem>

    @Upsert
    fun addToFeaturedPartnerItems(featuredPartnersItem: FeaturedPartnersItem)

}