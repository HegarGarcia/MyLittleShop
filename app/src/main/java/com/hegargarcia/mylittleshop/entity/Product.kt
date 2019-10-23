package com.hegargarcia.mylittleshop.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("id")])
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var name: String,
    var description: String,
    var amount: Int,
    var price: Float,
    var cost: Float,
    @ColumnInfo(name = "store_name") val storeName: String,
    @ColumnInfo(name = "photo_url") var photoUrl: String
)