package com.example.iamwaiter.model.repositories

import com.example.iamwaiter.model.dao.UserDao
import com.example.iamwaiter.model.entities.User

class UserRepository(private val dao: UserDao) {

    fun getAllUsers(): List<User> {
        return dao.getAll()
    }

    fun addUser(user: User) {
        dao.insert(user)
    }

    fun deleteUser(user: User) {
        dao.delete(user)
    }

    fun deleteAllUsers(){
        dao.deleteAll()
    }

    fun updateUser(user: User) {
        dao.update(user)
    }

}