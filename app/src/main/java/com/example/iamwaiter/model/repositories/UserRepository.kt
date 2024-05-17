package com.example.iamwaiter.model.repositories

import com.example.iamwaiter.model.dao.UserDao
import com.example.iamwaiter.model.entities.User

class UserRepository(private val userDao: UserDao) {

    fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    fun addUser(user: User) {
        userDao.insert(user)
    }

    fun deleteUser(user: User) {
        userDao.delete(user)
    }

    fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }

    fun updateUser(user: User) {
        userDao.update(user)
    }

}