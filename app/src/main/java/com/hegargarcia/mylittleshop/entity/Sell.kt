package com.hegargarcia.mylittleshop.entity

import androidx.room.*
import java.time.LocalDateTime

@Entity(
    indices = [Index("client"), Index("product")],
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
    var amount: Int,
    var total: Float,
    var cost: Float,
    var date: LocalDateTime?,
    @ColumnInfo(name = "store_name") val storeName: String
    )