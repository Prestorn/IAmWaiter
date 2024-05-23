package com.example.iamwaiter.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.iamwaiter.model.dao.DishDao
import com.example.iamwaiter.model.entities.Dish

class DishRepository(private val dao: DishDao) {

    fun getAllDishes(): LiveData<List<Dish>> {
        return dao.getAll()
    }

    fun getDishById(id: Int): LiveData<Dish> {
        return dao.getDishById(id)
    }

    fun getDishValueById(id: Int): Dish {
        return dao.getDishValueById(id)
    }

    fun getDishListValueByIdList(id: Int): List<Dish> {
        return dao.getDishListValueByOrderId(id)
    }

    fun getDishListByOrderId(id: Int): LiveData<List<Dish>> {
        return dao.getDishListByOrderId(id)
    }

    fun getDishListByCategoryID(id: Int): List<Dish> {
        return dao.getDishListByCategoryID(id)
    }

    fun getDishListLiveDataByCategoryID(id: Int): LiveData<List<Dish>> {
        return dao.getDishListLiveDataByCategoryID(id)
    }

    fun addDish(dish: Dish) {
        dao.insert(dish)
    }

    fun deleteDish(dish: Dish) {
        dao.delete(dish)
    }

    fun deleteAllDishes(){
        dao.deleteAll()
    }

    fun updateDish(dish: Dish) {
        dao.update(dish)
    }

}