package com.hegargarcia.mylittleshop.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("id")])
data class Client(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var name: String,
    var address: String,
    var phone: String,
    var email: String
)