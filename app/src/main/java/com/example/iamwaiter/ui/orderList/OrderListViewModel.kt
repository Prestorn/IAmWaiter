package com.example.iamwaiter.ui.orderList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.iamwaiter.model.DataBase
import com.example.iamwaiter.model.entities.Order
import com.example.iamwaiter.model.entities.User
import com.example.iamwaiter.model.repositories.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderListViewModel(application: Application) : AndroidViewModel(application){
    val user = MutableLiveData<User>()
    val selectedOrderId = MutableLiveData<Int>()
    var newOrder: Order? = null

    private val orderDao = DataBase.getDatabase(application).orderDao()
    private val orderRepository = OrderRepository(orderDao)

    var orderList: LiveData<List<Order>> = orderRepository.getOrdersByUserId(1)
    var orderListValue: List<Order> = listOf()

    var peopleCountList = ArrayList<Int>()
    var tableNumberList = ArrayList<Int>()
    var costList = ArrayList<Int>()
    var idList = ArrayList<Int>()

    fun updateOrderList() {
        viewModelScope.launch(Dispatchers.IO) {
            orderListValue = orderRepository.getOrdersValueByUserId(user.value!!.id)
            orderList = orderRepository.getOrdersByUserId(user.value!!.id)
        }
    }

    fun createOrder() {
        viewModelScope.launch(Dispatchers.IO) {
            orderRepository.addOrder(Order(0, 1, user.value!!.id, 0, 0, 0))
            updateOrderList()
        }
    }

    fun deleteOrder(id: Int) {
        var selectedOrder: Order? = null
        orderListValue.forEach {
            if (it.id == id) {
                selectedOrder = it
                return@forEach
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            orderRepository.deleteOrder(selectedOrder!!)
        }
        updateOrderList()
    }

    fun updateLists() {
        clearLists()
        orderListValue.forEach { order ->
            peopleCountList.add(order.peopleCount)
            tableNumberList.add(order.tableId)
            costList.add(order.cost)
            idList.add(order.id)
        }
    }

    private fun clearLists() {
        peopleCountList.clear()
        tableNumberList.clear()
        costList.clear()
        idList.clear()
    }
}