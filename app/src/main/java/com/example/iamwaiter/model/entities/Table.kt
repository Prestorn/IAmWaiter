package com.example.iamwaiter.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table")
data class Table(
    @PrimaryKey
    val id:Int,
    val statusId:Int,
    val peopleCount:Int
){}