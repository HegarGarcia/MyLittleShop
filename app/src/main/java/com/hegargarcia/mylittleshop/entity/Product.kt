package com.hegargarcia.mylittleshop.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var name: String,
    var description: String,
    var amount: Int,
    var price: Float,
    var cost: Float,
    @ColumnInfo(name = "photo_url") var photoUrl: String
)