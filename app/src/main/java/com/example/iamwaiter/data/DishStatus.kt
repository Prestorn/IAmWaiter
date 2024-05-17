package com.example.iamwaiter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishStatus")
data class DishStatus (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val description:String
){}