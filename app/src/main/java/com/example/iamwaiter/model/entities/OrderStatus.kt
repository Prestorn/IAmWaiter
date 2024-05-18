package com.example.iamwaiter.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orderStatus")
data class OrderStatus(
    @PrimaryKey
    val id:Int,
    val description:String
){}