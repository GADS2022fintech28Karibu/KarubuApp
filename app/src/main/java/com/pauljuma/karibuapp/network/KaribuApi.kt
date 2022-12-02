package com.pauljuma.karibuapp.network

import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.data.FeaturedPartnersItem
import retrofit2.http.GET

interface KaribuApi {

    @GET("continents")
    suspend fun getFeaturedPartners(): List<FeaturedPartnersItem>

    @GET("characters")
    suspend fun getFavoriteItems(): List<FavoriteMealsItem>
}