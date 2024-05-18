package com.example.iamwaiter.model

import com.example.iamwaiter.model.entities.User

class Data {
    companion object{
        val users = listOf<User>(
            User(0, "Alexander", "Yurkov", "test", "test")
        )
    }
}