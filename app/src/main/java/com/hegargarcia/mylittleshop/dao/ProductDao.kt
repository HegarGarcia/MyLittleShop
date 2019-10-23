package com.hegargarcia.mylittleshop.dao

import androidx.room.*
import com.hegargarcia.mylittleshop.entity.Product

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Query("SELECT * FROM Product WHERE id == :id AND store_name = :storeName")
    fun getById(id: Int, storeName:String): Product

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM Product WHERE store_name = :storeName")
    fun getAll(storeName: String): List<Product>
}