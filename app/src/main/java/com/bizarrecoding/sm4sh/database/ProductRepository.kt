package com.bizarrecoding.sm4sh.database

import androidx.lifecycle.LiveData
import com.bizarrecoding.sm4sh.models.Brand
import com.bizarrecoding.sm4sh.models.Product

interface ProductRepository {

    fun observeProducts(): LiveData<List<Product>>

    fun observePopular(): LiveData<List<Product>>

    fun observeNew(): LiveData<List<Product>>

    fun observeProductsByBrand(brand: String?): LiveData<List<Product>>

    fun observeBrands(): LiveData<List<Brand>>

    suspend fun refreshProducts()

    suspend fun loadConfig():Int

    suspend fun getProducts(): List<Product>
}