package com.example.iamwaiter.ui.orderList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.iamwaiter.data.Order

class OrderListViewModel : ViewModel(){
    val orderList = MutableLiveData<ArrayList<Order>>()
    fun updateOrderList(){
        orderList.value =  arrayListOf(
            Order(10, 1, 1, 1, 460),
            Order(20, 2, 1, 2, 1080),
            Order(30, 4, 1, 5, 1220),
            Order(40, 1, 1, 3, 890),
            Order(50, 1, 1, 4, 2060)
        )
    }

}