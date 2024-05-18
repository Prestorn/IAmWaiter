package com.example.iamwaiter.model.repositories

import com.example.iamwaiter.model.dao.DishInOrderDao
import com.example.iamwaiter.model.entities.DishInOrder

class DishInOrderRepository(private val dao: DishInOrderDao) {

    fun getAllDishesInOrders(): List<DishInOrder> {
        return dao.getAll()
    }

    fun addDishInOrder(dishInOrder: DishInOrder) {
        dao.insert(dishInOrder)
    }

    fun deleteDishInOrder(dishInOrder: DishInOrder) {
        dao.delete(dishInOrder)
    }

    fun deleteAllDishesInOrders(){
        dao.deleteAll()
    }

    fun updateDishInOrder(dishInOrder: DishInOrder) {
        dao.update(dishInOrder)
    }

}