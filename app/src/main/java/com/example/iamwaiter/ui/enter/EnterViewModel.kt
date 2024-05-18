package com.example.iamwaiter.ui.enter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.iamwaiter.model.Data
import com.example.iamwaiter.model.DataBase
import com.example.iamwaiter.model.entities.User
import com.example.iamwaiter.model.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnterViewModel(application: Application) : AndroidViewModel(application) {

    val user = MutableLiveData<User>()
    private val userDao = DataBase.getDatabase(application).userDao()
    private val userRepository = UserRepository(userDao)
    private var usersList = List<User?>(1){null}

    init {
        checkDataBaseFilling()
    }

    fun checkUserLogin(login: String, password: String): Boolean {
        updateUsersList()
        usersList.forEach { user ->
            if (user?.login == login && user.password == password) {
                this.user.value = user
                return true
            }
        }
        return false
    }

    private fun updateUsersList(){
        viewModelScope.launch(Dispatchers.IO) {
            usersList = userRepository.getAllUsers()
        }
    }

    private fun checkDataBaseFilling(){
        viewModelScope.launch(Dispatchers.IO) {
            usersList = userRepository.getAllUsers()
            if (usersList.isEmpty()){
                fillDataBase()
                usersList = userRepository.getAllUsers()
            }
        }
    }

    private fun fillDataBase() {
        Data.users.forEach{userRepository.addUser(it) }
    }

    private fun clearDataBase(){

    }

}