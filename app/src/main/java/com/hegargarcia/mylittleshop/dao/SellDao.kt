package com.hegargarcia.mylittleshop.dao

import androidx.room.*
import com.hegargarcia.mylittleshop.entity.Sell
import java.time.LocalDateTime

@Dao
interface SellDao {
    @Insert
    fun insert(sell: Sell)

    @Query("SELECT * FROM Sell WHERE id == :id AND store_name = :storeName")
    fun getById(id: Int, storeName: String): Sell

    @Update
    fun update(sell: Sell)

    @Delete
    fun delete(sell: Sell)

    @Query("SELECT * FROM Sell WHERE store_name = :storeName")
    fun getAll(storeName: String): List<Sell>

    @Query("SELECT * FROM Sell WHERE date BETWEEN :to AND :from AND store_name = :storeName")
    fun findSellsBetweenDates(from: LocalDateTime, to: LocalDateTime, storeName: String): List<Sell>
}