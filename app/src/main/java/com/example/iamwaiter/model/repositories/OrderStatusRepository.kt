package com.example.iamwaiter.model.repositories

import androidx.lifecycle.LiveData
import com.example.iamwaiter.model.dao.OrderStatusDao
import com.example.iamwaiter.model.entities.OrderStatus

class OrderStatusRepository(private val dao: OrderStatusDao) {
    fun getAllOrderStatuses(): List<OrderStatus> {
        return dao.getAll()
    }

    fun addOrderStatus(orderStatus: OrderStatus){
        dao.insert(orderStatus)
    }

    fun deleteOrderStatus(orderStatus: OrderStatus){
        dao.delete(orderStatus)
    }

    fun deleteAllOrderStatuses(){
        dao.deleteAll()
    }

    fun updateOrderStatus(orderStatus: OrderStatus){
        dao.update(orderStatus)
    }
}