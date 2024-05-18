package com.example.iamwaiter.model.repositories

import androidx.lifecycle.LiveData
import com.example.iamwaiter.model.dao.OrderDao
import com.example.iamwaiter.model.entities.Order

class OrderRepository(private val dao: OrderDao) {
    fun getAllOrders(): List<Order> {
        return dao.getAll()
    }

    fun addOrder(order: Order){
        dao.insert(order)
    }

    fun deleteOrder(order: Order){
        dao.delete(order)
    }

    fun deleteAllOrders(){
        dao.deleteAll()
    }

    fun updateOrder(order: Order){
        dao.update(order)
    }
}