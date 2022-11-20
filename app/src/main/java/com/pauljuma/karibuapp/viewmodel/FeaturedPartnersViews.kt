package com.pauljuma.karibuapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.repository.KaribuRepository
import kotlinx.coroutines.launch

class FeaturedPartnersViews(val repository: KaribuRepository): ViewModel() {
    val featuredPartnerItems: MutableLiveData<List<FavoriteMealsItem>> = MutableLiveData()

    init{
        getFeaturedPartnerItem()
    }

    fun getFeaturedPartnerItem() = viewModelScope.launch {
        val response = repository.getFavoriteItem()
        featuredPartnerItems.value = response
    }
}