package com.bizarrecoding.sm4sh.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bizarrecoding.sm4sh.models.Brand
import com.bizarrecoding.sm4sh.models.Config
import com.bizarrecoding.sm4sh.models.Product

@Dao
interface SmashDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(vararg product: Product): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConfigParam(config: Config): Long

    @Query("SELECT * FROM Products WHERE ObjectID = :key")
    fun get(key: String): LiveData<Product>?

    ///List of products
    @Query("SELECT * FROM Products ORDER BY ObjectId DESC")
    fun observeProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM Products ORDER BY ObjectId DESC")
    suspend fun getProducts(): List<Product>

    @Query("SELECT COUNT(*) FROM Products")
    fun countProducts(): Int

    @Query("SELECT * FROM Products WHERE universe = :brand ORDER BY ObjectId DESC")
    fun observeProductsByBrand(brand: String): LiveData<List<Product>>

    @Query("SELECT * FROM Products ORDER BY createdAt DESC LIMIT 5")
    fun observeNewProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM Products WHERE popular = 1 ORDER BY ObjectId DESC")
    fun observePopularProducts(): LiveData<List<Product>>

    @Query("SELECT universe FROM Products WHERE universe NOT NULL GROUP BY universe ORDER BY universe ASC")
    fun observeBrands(): LiveData<List<Brand>>
}
