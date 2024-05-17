package com.example.iamwaiter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishInCategory")
data class DishInCategory(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val dishId:Int,
    val categoryId:Int
) {}