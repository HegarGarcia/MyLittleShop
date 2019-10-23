package com.hegargarcia.mylittleshop.dao

import androidx.room.*
import com.hegargarcia.mylittleshop.entity.Client

@Dao
interface ClientDao {
    @Insert
    fun insert(client: Client)

    @Query("SELECT * FROM Client WHERE id == :id AND store_name = :storeName")
    fun getById(id: Int, storeName: String): Client

    @Update
    fun update(client: Client)

    @Delete
    fun delete(client: Client)

    @Query("SELECT * FROM Client WHERE store_name = :storeName")
    fun getAll(storeName: String): List<Client>
}