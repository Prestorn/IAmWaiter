package com.example.iamwaiter.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableStatus")
data class TableStatus(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val description:String
){}