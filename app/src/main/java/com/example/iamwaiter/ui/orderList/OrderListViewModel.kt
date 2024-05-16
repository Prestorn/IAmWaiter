package com.example.iamwaiter.ui.orderList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iamwaiter.data.Order

class OrderListViewModel : ViewModel(){
    val orderList = MutableLiveData<ArrayList<Order>>()
    fun updateOrderList(){
        orderList.value =  arrayListOf(
            Order(1, 1, 1, 1, 460),
            Order(2, 2, 1, 2, 1080),
            Order(3, 4, 1, 5, 1220),
            Order(4, 1, 1, 3, 890),
            Order(5, 1, 1, 4, 2060)
        )
    }
}