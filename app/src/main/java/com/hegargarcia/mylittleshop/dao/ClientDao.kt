package com.hegargarcia.mylittleshop.dao

import androidx.room.*
import com.hegargarcia.mylittleshop.entity.Client

@Dao
interface ClientDao {
    @Insert
    fun insert(client: Client)

    @Query("SELECT * FROM Client WHERE id == :id")
    fun getById(id: Int): Client

    @Update
    fun update(client: Client)

    @Delete
    fun delete(client: Client)

    @Query("SELECT * FROM Client")
    fun getAll(): List<Client>
}