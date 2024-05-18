package com.example.iamwaiter.model.repositories

import androidx.lifecycle.LiveData
import com.example.iamwaiter.model.dao.DishDao
import com.example.iamwaiter.model.entities.Dish

class DishRepository(private val dao: DishDao) {

    fun getAllDishes(): LiveData<List<Dish>> {
        return dao.getAll()
    }

    fun getDishById(id: Int): Dish {
        return dao.getDishById(id)
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