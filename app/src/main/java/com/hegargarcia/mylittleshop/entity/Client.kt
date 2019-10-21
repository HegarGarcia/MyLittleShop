package com.hegargarcia.mylittleshop.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Client(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var name: String,
    var address: String,
    var phone: String,
    var email: String
)