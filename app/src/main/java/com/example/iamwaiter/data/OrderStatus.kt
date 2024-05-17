package com.example.iamwaiter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orderStatus")
data class OrderStatus(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val description:String
){}