package com.hegargarcia.mylittleshop.dao

import androidx.room.*
import com.hegargarcia.mylittleshop.entity.Product

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Query("SELECT * FROM Product WHERE id == :id")
    fun getById(id: Int): Product

    @Update
    fun update(product: Product)

    @Query("DELETE FROM Product WHERE id == :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>
}