package com.hegargarcia.mylittleshop.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val username: String,
    val password: String,
    val email: String,
    @ColumnInfo(name = "store_name") val storeName: String
)