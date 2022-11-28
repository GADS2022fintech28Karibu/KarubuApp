package com.pauljuma.karibuapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pauljuma.karibuapp.repository.KaribuRepository

class FeaturedPartnerViewsFactory(val repository: KaribuRepository): ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FeaturedPartnersViews(repository) as T
    }
}