package com.example.iamwaiter.ui.orderScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OrderScreenViewModel : ViewModel() {
    val orderId = MutableLiveData<Int>()

}