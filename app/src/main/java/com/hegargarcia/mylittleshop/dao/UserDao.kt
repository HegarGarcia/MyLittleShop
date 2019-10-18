package com.hegargarcia.mylittleshop.dao

import androidx.room.*
import com.hegargarcia.mylittleshop.entity.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User): Int

    @Query("SELECT * FROM User WHERE id == :id")
    fun getById(id: Int): User

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User WHERE email == :email AND password == :password")
    fun login(email: String, password: String): User
}