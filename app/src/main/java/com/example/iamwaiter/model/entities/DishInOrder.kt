package com.example.iamwaiter.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishInOrder")
data class DishInOrder(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val dishId:Int,
    val orderId:Int,
    val statusId:Int,
    val count:Int
) {}