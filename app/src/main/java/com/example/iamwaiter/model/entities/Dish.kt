package com.example.iamwaiter.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish")
data class Dish(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val description:String,
    val cost:Int,
    val weight:Int
){}