package com.bizarrecoding.sm4sh.screens.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bizarrecoding.sm4sh.database.Product

@Suppress("UNCHECKED_CAST")
class ProductViewModelFactory(private val producto: Product): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(producto) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}