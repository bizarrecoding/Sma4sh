package com.bizarrecoding.sm4sh.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bizarrecoding.sm4sh.database.ProductRepository
import com.bizarrecoding.sm4sh.screens.catalogue.CatalogueViewModel
import com.bizarrecoding.sm4sh.screens.preferences.PreferencesViewModel
import com.bizarrecoding.sm4sh.screens.product.ProductViewModel
import com.bizarrecoding.sm4sh.screens.splash.SplashViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: ProductRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = with(modelClass){
        when{
            isAssignableFrom(CatalogueViewModel::class.java)->
                CatalogueViewModel(repository)
            isAssignableFrom(ProductViewModel::class.java)->
                ProductViewModel()
            isAssignableFrom(SplashViewModel::class.java)->
                SplashViewModel(repository)
            isAssignableFrom(PreferencesViewModel::class.java)->
                PreferencesViewModel(repository)
            else->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}