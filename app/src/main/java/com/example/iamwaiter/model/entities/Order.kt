package com.example.iamwaiter.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order")
data class Order (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val statusId:Int,
    val userId:Int,
    val tableId:Int,
    val cost:Int){}