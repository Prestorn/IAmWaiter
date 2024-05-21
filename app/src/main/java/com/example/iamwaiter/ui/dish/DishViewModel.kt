package com.example.iamwaiter.ui.dish

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.iamwaiter.model.DataBase
import com.example.iamwaiter.model.entities.Dish
import com.example.iamwaiter.model.repositories.DishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DishViewModel(application: Application) : AndroidViewModel(application) {

    var dishId = MutableLiveData<Int>()
    var dishIdValue = 0

    val dishDao = DataBase.getDatabase(application).dishDao()
    val dishRepository = DishRepository(dishDao)

    var dishLiveData: LiveData<Dish> = dishRepository.getDishById(0)
    var dish: Dish = Dish(0, "", "", 0, 0)

    fun updateDish() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("dishIdValue", "$dishIdValue")
            dish = dishRepository.getDishValueById(dishIdValue)
            dishLiveData = dishRepository.getDishById(dishIdValue)
        }
    }
}