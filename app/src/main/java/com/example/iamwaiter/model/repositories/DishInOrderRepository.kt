package com.example.iamwaiter.model.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.iamwaiter.model.dao.DishInOrderDao
import com.example.iamwaiter.model.entities.DishInOrder

class DishInOrderRepository(private val dao: DishInOrderDao) {

    fun getAllDishesInOrders(): LiveData<List<DishInOrder>> {
        return dao.getAll()
    }

    fun getAllDishesInOrder(id: Int): LiveData<List<DishInOrder>> {
        Log.i("Order ID", "$id")
        return dao.getAllByOrderId(id)
    }

    fun getAllDishesValueInOrder(id: Int): List<DishInOrder> {
        Log.i("Order ID", "$id")
        return dao.getAllValueByOrderId(id)
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