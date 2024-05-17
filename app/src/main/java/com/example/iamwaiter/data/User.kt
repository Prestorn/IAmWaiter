package com.example.iamwaiter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val surname:String,
    val login:String,
    val password:String
) {}