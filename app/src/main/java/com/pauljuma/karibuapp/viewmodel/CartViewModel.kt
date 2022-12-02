package com.pauljuma.karibuapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pauljuma.karibuapp.data.CartItem
import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.repository.KaribuRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(val repository: KaribuRepository): ViewModel() {
    val cartItems: MutableLiveData<List<CartItem>> = MutableLiveData()
    val favoriteMeals: MutableLiveData<List<FavoriteMealsItem>> = MutableLiveData()

    init {
        getFavoriteMealsItem()
        getCartItem()
    }

    fun addToCart(cartItem: CartItem) = viewModelScope.launch {
        repository.addCartItem(cartItem)
    }

    fun getCartItem() = viewModelScope.launch {
        val response = repository.getCartItem()
        cartItems.value = response
    }

    fun getFavoriteMealsItem() = viewModelScope.launch {
        val response = repository.getFavoriteItem()
        favoriteMeals.value = response
    }
    fun delete(cartItem: CartItem) = CoroutineScope(Dispatchers.Main).launch{
        repository.delete(cartItem)
    }

    fun addFavoriteMeal(favoriteMeals: FavoriteMealsItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.addFavoriteMeal(favoriteMeals)
    }
}