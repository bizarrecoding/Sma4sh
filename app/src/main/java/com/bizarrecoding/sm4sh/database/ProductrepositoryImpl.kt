package com.bizarrecoding.sm4sh.database

import android.util.Log
import androidx.lifecycle.LiveData
import com.bizarrecoding.sm4sh.models.Brand
import com.bizarrecoding.sm4sh.models.Product
import com.bizarrecoding.sm4sh.smashApi.SmashService
import com.bizarrecoding.sm4sh.smashApi.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(private val dao: SmashDao): ProductRepository {

    override fun observeProducts(): LiveData<List<Product>> = dao.observeProducts()

    override fun observePopular(): LiveData<List<Product>> = dao.observePopularProducts()

    override fun observeNew(): LiveData<List<Product>> = dao.observeNewProducts()

    override fun observeProductsByBrand(brand: String?): LiveData<List<Product>> {
        return if(brand != null && brand != "All"){
            dao.observeProductsByBrand(brand)
        }else{
            observeProducts()
        }
    }

    override fun observeBrands(): LiveData<List<Brand>> = dao.observeBrands()

    override suspend fun refreshProducts(){
        withContext(Dispatchers.IO){
            val products = SmashService.api.getProducts().asDatabaseModel()
            val inserted = dao.insertProducts(*products)
            Log.d("HRK_api_inserted","inserted ${inserted.size} Products")
        }
    }

    override suspend fun loadConfig(): Int {
        return dao.countProducts()
    }

    override suspend fun getProducts(): List<Product> {
        return dao.getProducts()
    }
}