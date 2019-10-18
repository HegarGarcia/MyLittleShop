package com.hegargarcia.mylittleshop.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val description: String,
    val amount: Int,
    val price: Float,
    val cost: Float,
    @ColumnInfo(name = "photo_url") val photoUrl: String
)