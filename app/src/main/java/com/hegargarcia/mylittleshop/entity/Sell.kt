package com.hegargarcia.mylittleshop.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    foreignKeys = [ForeignKey(
        entity = Client::class,
        parentColumns = ["id"],
        childColumns = ["client"]
    ), ForeignKey(
        entity = Product::class,
        parentColumns = ["id"],
        childColumns = ["product"]
    )]
)
data class Sell(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val client: String,
    val product: String,
    val amount: Int,
    val total: Float,
    val cost: Float,
    val date: Date
)