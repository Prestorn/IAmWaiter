package com.example.iamwaiter.model

import com.example.iamwaiter.model.entities.User

class BaseData {
    companion object{
        val users = listOf<User>(
            User(0, "Alexander", "Yurkov", "test", "test")
        )
    }
}