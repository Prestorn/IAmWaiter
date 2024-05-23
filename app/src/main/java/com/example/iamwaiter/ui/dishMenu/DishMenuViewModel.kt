package com.example.iamwaiter.ui.dishMenu

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.iamwaiter.model.DataBase
import com.example.iamwaiter.model.entities.Dish
import com.example.iamwaiter.model.repositories.DishInCategoryRepository
import com.example.iamwaiter.model.repositories.DishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DishMenuViewModel(application: Application): AndroidViewModel(application) {

    val categoryId = MutableLiveData<Int>()
    var categoryIdValue = 0

    val navigateFromMenu = MutableLiveData<Boolean>()
    var navigateFromOrder = false

    val dishDao = DataBase.getDatabase(application).dishDao()
    val dishRepository = DishRepository(dishDao)

    var dishList = listOf<Dish>()
    var dishListLiveData: LiveData<Dish> = dishRepository.getDishById(1)

    val namesList = mutableListOf<String>()
    val costList = mutableListOf<Int>()
    val weightList = mutableListOf<Int>()
    val idList = mutableListOf<Int>()

    fun updateDishInCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            dishList = dishRepository.getDishListByCategoryID(categoryIdValue)
            dishListLiveData = dishRepository.getDishById(1)
        }
    }

    fun updateLists() {
        clearLists()
        dishList.forEach {
            namesList.add(it.name)
            costList.add(it.cost)
            weightList.add(it.weight)
            idList.add(it.id)
        }
    }

    private fun clearLists() {
        namesList.clear()
        costList.clear()
        weightList.clear()
        idList.clear()
    }
}