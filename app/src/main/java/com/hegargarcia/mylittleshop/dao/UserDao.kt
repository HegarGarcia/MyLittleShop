package com.hegargarcia.mylittleshop.dao

import androidx.room.*
import com.hegargarcia.mylittleshop.entity.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User): Long

    @Query("SELECT * FROM User WHERE id = :id")
    fun getById(id: Int): User

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User WHERE username = :username AND password = :password AND store_name = :storeName")
    fun login(username: String, password: String, storeName: String): User

    @Query("SELECT store_name FROM User")
    fun getStoreNames(): List<String>
}