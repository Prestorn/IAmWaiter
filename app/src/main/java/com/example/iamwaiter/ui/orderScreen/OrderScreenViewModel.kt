package com.example.iamwaiter.ui.orderScreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.iamwaiter.model.DataBase
import com.example.iamwaiter.model.entities.Dish
import com.example.iamwaiter.model.entities.DishInOrder
import com.example.iamwaiter.model.entities.Order
import com.example.iamwaiter.model.repositories.DishInOrderRepository
import com.example.iamwaiter.model.repositories.DishRepository
import com.example.iamwaiter.model.repositories.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderScreenViewModel(application: Application) : AndroidViewModel(application) {
    val orderId = MutableLiveData<Int>()
    val addDishInOrderEnable = MutableLiveData<Boolean>()
    private var order: Order? = null

    private val dishInOrderDao = DataBase.getDatabase(application).dishInOrderDao()
    private val dishInOrderRepository = DishInOrderRepository(dishInOrderDao)

    private val dishDao = DataBase.getDatabase(application).dishDao()
    private val dishRepository = DishRepository(dishDao)

    private val orderDao = DataBase.getDatabase(application).orderDao()
    private val orderRepository = OrderRepository(orderDao)

    var dishInOrderList: List<DishInOrder> = listOf()
    var dishListLiveData: LiveData<List<Dish>> = dishRepository.getDishListByOrderId(1)
    var dishList: List<Dish> = listOf()

    val countList: ArrayList<Int> = arrayListOf()
    val namesList: ArrayList<String> = arrayListOf()
    val costList: ArrayList<Int> = arrayListOf()
    val statusesList: ArrayList<Int> = arrayListOf()
    val idList: ArrayList<Int> = arrayListOf()
    var orderCost: Int = 0
    var peopleCount: Int = 0
    var tableNumber: Int = 0

    fun updateOrder(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            order = orderRepository.getOrderById(id)
            peopleCount = order!!.peopleCount
            tableNumber = order!!.tableId
            updateDishInOrderList()
        }
    }

    fun updateDishInOrderList() {
        viewModelScope.launch(Dispatchers.IO) {
            dishInOrderList = dishInOrderRepository.getAllDishesValueInOrder(order!!.id)
            updateDishList()
        }
    }

    fun updateDishList() {
        viewModelScope.launch(Dispatchers.IO) {
            dishList = dishRepository.getDishListValueByIdList(order!!.id)
            dishListLiveData = dishRepository.getDishListByOrderId(order!!.id)
        }
    }


    fun updateLists() {
        clearLists()
        for (i in (0 .. dishList.size - 1) ) {
            countList.add(dishInOrderList[i].count)
            statusesList.add(dishInOrderList[i].statusId)
            idList.add(dishInOrderList[i].dishId)
            namesList.add(dishList[i].name)
            costList.add(dishList[i].cost * countList[i])
            orderCost += dishList[i].cost * countList[i]
        }
    }

    fun clearLists(){
        countList.clear()
        statusesList.clear()
        namesList.clear()
        costList.clear()
        idList.clear()
        orderCost = 0
    }

}