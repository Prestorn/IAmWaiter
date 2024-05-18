package com.example.iamwaiter.ui.orderScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    private val dishInOrderDao = DataBase.getDatabase(application).dishInOrderDao()
    private val dishInOrderRepository = DishInOrderRepository(dishInOrderDao)

    private val dishDao = DataBase.getDatabase(application).dishDao()
    private val dishRepository = DishRepository(dishDao)

    private val orderDao = DataBase.getDatabase(application).orderDao()
    private val orderRepository = OrderRepository(orderDao)

    var dishInOrderList: List<DishInOrder> = listOf()
    var dishList: MutableList<Dish> = mutableListOf()
    val listIsFinished: MutableLiveData<Boolean> = MutableLiveData(false)
    var order: Order? = null

    val countList: ArrayList<Int> = arrayListOf()
    val namesList: ArrayList<String> = arrayListOf()
    val costList: ArrayList<Int> = arrayListOf()
    val statusesList: ArrayList<Int> = arrayListOf()
    val idList: ArrayList<Int> = arrayListOf()
    var c = 1
    var orderCost: Int = 0
    var peopleCount: Int = 0
    var tableNumber: Int = 0

    fun updateDishInOrderList() {
        listIsFinished.value = false
        viewModelScope.launch(Dispatchers.IO) {
            dishInOrderList = dishInOrderRepository.getAllDishesInOrder(orderId.value!!)
            order = orderRepository.getOrderById(orderId.value!!)
            peopleCount = order!!.peopleCount
            tableNumber = order!!.tableId
            fillDishList()
        }
        checkDishListSize()
    }

    private fun fillDishList(){
        dishList.clear()
        dishInOrderList.forEach{
            dishList.add(dishRepository.getDishById(it.dishId))
        }
    }

    private fun checkDishListSize(){
        c = 0
        if (dishList.size != dishInOrderList.size || dishList.isEmpty()){
            c++
            checkDishListSize()
        }
        else{
            listIsFinished.value = true
        }
    }

    fun fillLists(){
        clearLists()
        for (i in (0 .. dishList.size - 1) ){
            countList.add(dishInOrderList[i].count)
            statusesList.add(dishInOrderList[i].statusId)
            namesList.add(dishList[i].name)
            costList.add(dishList[i].cost * dishInOrderList[i].count)
            idList.add(dishList[i].id)
            orderCost += dishList[i].cost * dishInOrderList[i].count
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