package com.hegargarcia.mylittleshop.dao

import androidx.room.*
import com.hegargarcia.mylittleshop.entity.Sell

@Dao
interface SellDao {
    @Insert
    fun insert(sell: Sell)

    @Query("SELECT * FROM Sell WHERE id == :id")
    fun getById(id: Int): Sell

    @Update
    fun update(sell: Sell)

    @Delete
    fun delete(sell: Sell)
}