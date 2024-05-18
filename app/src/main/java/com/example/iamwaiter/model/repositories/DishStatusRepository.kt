package com.example.iamwaiter.model.repositories

import com.example.iamwaiter.model.dao.DishStatusDao
import com.example.iamwaiter.model.entities.DishStatus

class DishStatusRepository(private val dao: DishStatusDao) {

    fun getAllDishStatuses(): List<DishStatus> {
        return dao.getAll()
    }

    fun addDishStatus(dishStatus: DishStatus) {
        dao.insert(dishStatus)
    }

    fun deleteDishStatus(dishStatus: DishStatus) {
        dao.delete(dishStatus)
    }

    fun deleteAllDishStatuses(){
        dao.deleteAll()
    }

    fun updateDishInOrder(dishStatus: DishStatus) {
        dao.update(dishStatus)
    }

}