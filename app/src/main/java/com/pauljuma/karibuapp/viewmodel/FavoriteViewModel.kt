package com.pauljuma.karibuapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pauljuma.karibuapp.data.CartItem
import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.data.FeaturedPartnersItem
import com.pauljuma.karibuapp.repository.KaribuRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(val repository: KaribuRepository) : ViewModel() {
    val featuredPartners: MutableLiveData<List<FeaturedPartnersItem>> = MutableLiveData()
    val favoriteMeals: MutableLiveData<List<FavoriteMealsItem>> = MutableLiveData()

    init {
        getFeaturedPartnersItems()
        getFavoriteMealsItem()
    }

    private fun getFeaturedPartnersItems() = viewModelScope.launch {
        val response = repository.getItems()
        featuredPartners.value = response
    }

    fun getFavoriteMealsItem() = viewModelScope.launch {
        val response = repository.getFavoriteItem()
        favoriteMeals.value = response
    }
}