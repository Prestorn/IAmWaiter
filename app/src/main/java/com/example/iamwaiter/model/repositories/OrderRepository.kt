package com.example.iamwaiter.model.repositories

import androidx.lifecycle.LiveData
import com.example.iamwaiter.model.dao.OrderDao
import com.example.iamwaiter.model.entities.Order

class OrderRepository(private val dao: OrderDao) {
    fun getAllOrders(): LiveData<List<Order>> {
        return dao.getAll()
    }

    fun getOrderById(id: Int): Order {
        return dao.getOrderById(id)
    }

    fun getOrdersByUserId(id: Int): LiveData<List<Order>> {
        return dao.getOrdersByUserId(id)
    }

    fun getOrdersValueByUserId(id: Int): List<Order> {
        return dao.getOrdersValueByUserId(id)
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