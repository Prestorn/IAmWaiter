package com.example.iamwaiter.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishStatus")
data class DishStatus (
    @PrimaryKey
    val id:Int,
    val description:String
){}