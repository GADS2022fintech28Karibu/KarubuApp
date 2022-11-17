package com.pauljuma.karibuapp.network

import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.data.FeaturedPartnersItem
import retrofit2.http.GET

interface KaribuApi {

    @GET("v2/Continents")
    suspend fun getFeaturedPartners(): List<FeaturedPartnersItem>

    @GET("v2/Characters")
    suspend fun getFavoriteItems(): List<FavoriteMealsItem>
}