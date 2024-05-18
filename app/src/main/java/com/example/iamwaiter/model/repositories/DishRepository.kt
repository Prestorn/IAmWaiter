package com.example.iamwaiter.model.repositories

import com.example.iamwaiter.model.dao.DishDao
import com.example.iamwaiter.model.entities.Dish

class DishRepository(private val dao: DishDao) {

    fun getAllDishes(): List<Dish> {
        return dao.getAll()
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