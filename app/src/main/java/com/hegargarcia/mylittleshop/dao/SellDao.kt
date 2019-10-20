package com.hegargarcia.mylittleshop.dao

import androidx.room.*
import com.hegargarcia.mylittleshop.entity.Sell
import java.util.*

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

    @Query("SELECT * FROM Sell")
    fun getAll(): List<Sell>

    @Query("SELECT * FROM Sell WHERE date BETWEEN :from and :to")
    fun findSellsBetweenDates(from: Date, to: Date): List<Sell>

    @Query(
        """
        SELECT *, Product.description as productDescription, Client.name as clientName FROM Sell
        INNER JOIN Product
        ON Sell.product = Product.id
        INNER JOIN Client
        ON Sell.client = Client.id
        WHERE productDescription LIKE :searchString OR clientName LIKE :searchString
        """
    )
    fun search(searchString: String): List<Sell>
}