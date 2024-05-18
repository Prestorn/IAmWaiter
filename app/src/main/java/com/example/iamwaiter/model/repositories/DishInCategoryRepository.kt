package com.example.iamwaiter.model.repositories

import com.example.iamwaiter.model.dao.DishInCategoryDao
import com.example.iamwaiter.model.entities.DishInCategory

class DishInCategoryRepository(private val dao: DishInCategoryDao) {

    fun getAllDishesInCategeries(): List<DishInCategory> {
        return dao.getAll()
    }

    fun addDishInCategory(dishInCategory: DishInCategory) {
        dao.insert(dishInCategory)
    }

    fun deleteDishInCategory(dishInCategory: DishInCategory) {
        dao.delete(dishInCategory)
    }

    fun deleteAllDishesInCategories(){
        dao.deleteAll()
    }

    fun updateDishInCategory(dishInCategory: DishInCategory) {
        dao.update(dishInCategory)
    }

}