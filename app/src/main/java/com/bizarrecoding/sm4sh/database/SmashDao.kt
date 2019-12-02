package com.bizarrecoding.sm4sh.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SmashDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(vararg product: Product)

    @Update
    fun updateProduct(product: Product)

    @Query("SELECT * FROM Products WHERE ObjectID = :key")
    fun get(key: String): Product?

    @Query("SELECT * FROM Products ORDER BY ObjectId DESC")
    fun getProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM Products WHERE popular = 1 ORDER BY ObjectId DESC")
    fun getPopularProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM Products ORDER BY createdAt DESC LIMIT 5")
    fun getNewProducts(): LiveData<List<Product>>

    @Query("DELETE FROM Products")
    fun clear()
}
