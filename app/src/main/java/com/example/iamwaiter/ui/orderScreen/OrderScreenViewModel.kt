package com.example.iamwaiter.ui.orderScreen

import android.app.Application
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
import java.lang.NullPointerException

class OrderScreenViewModel(application: Application) : AndroidViewModel(application) {
    val orderId = MutableLiveData<Int>()
    private var order: Order? = null

    val addDishEnable = MutableLiveData<Boolean>()

    private val dishInOrderDao = DataBase.getDatabase(application).dishInOrderDao()
    private val dishInOrderRepository = DishInOrderRepository(dishInOrderDao)

    private val dishDao = DataBase.getDatabase(application).dishDao()
    private val dishRepository = DishRepository(dishDao)

    private val orderDao = DataBase.getDatabase(application).orderDao()
    private val orderRepository = OrderRepository(orderDao)

    private var dishInOrderList: List<DishInOrder> = listOf()
    var dishListLiveData: LiveData<List<Dish>> = dishRepository.getDishListByOrderId(1)
    private var dishList: List<Dish> = listOf()

    val countList: ArrayList<Int> = arrayListOf()
    val namesList: ArrayList<String> = arrayListOf()
    val costList: ArrayList<Int> = arrayListOf()
    val statusesList: ArrayList<Int> = arrayListOf()
    val idList: ArrayList<Int> = arrayListOf()
    var orderCost: Int = 0
    var peopleCount: Int = 0
    var tableNumber: Int = 0
    var maxPeopleCount: Int = 10
    var maxTableNumber: Int = 11

    fun updateOrder(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            order = orderRepository.getOrderById(id)
            peopleCount = order!!.peopleCount
            tableNumber = order!!.tableId
            updateDishInOrderList()
        }
    }

    private fun updateDishInOrderList() {
        viewModelScope.launch(Dispatchers.IO) {
            dishInOrderList = dishInOrderRepository.getAllDishesValueInOrder(order!!.id)
            updateDishList()
        }
    }

    private fun updateDishList() {
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
        updateOrderCostInDataBase()
    }

    private fun clearLists(){
        countList.clear()
        statusesList.clear()
        namesList.clear()
        costList.clear()
        idList.clear()
        orderCost = 0
    }

    private fun updateOrderCostInDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            order!!.cost = orderCost
            orderRepository.updateOrder(order!!)
        }
    }

    fun addDishInOrder(id: Int, count: Int) {
        var dishInOrderRecord: DishInOrder? = null
        try {
            if (id in idList) {
                dishInOrderList.forEach {
                    if (it.dishId == id) {
                        dishInOrderRecord = it
                        return@forEach
                    }
                }
                dishInOrderRecord!!.count += count
                dishInOrderRecord!!.statusId = 1
                viewModelScope.launch(Dispatchers.IO) {
                    dishInOrderRepository.updateDishInOrder(dishInOrderRecord!!)
                    updateDishInOrderList()
                }

            } else {
                dishInOrderRecord = DishInOrder(0, id, order!!.id, 1, count)
                viewModelScope.launch(Dispatchers.IO) {
                    dishInOrderRepository.addDishInOrder(dishInOrderRecord!!)
                }
            }
        } catch (e: NullPointerException) {
            throw e
        }
    }

    fun deleteDishFromOrder(id: Int, deleteAll: Boolean) {
        var dishInOrderRecord: DishInOrder? = null
        dishInOrderList.forEach {
            if (it.dishId == id) {
                dishInOrderRecord = it
                return@forEach
            }
        }
        if (deleteAll) {
            viewModelScope.launch(Dispatchers.IO) {
                dishInOrderRepository.deleteDishInOrder(dishInOrderRecord!!)
            }
        } else {
            if (dishInOrderRecord!!.count == 1) {
                viewModelScope.launch(Dispatchers.IO) {
                    dishInOrderRepository.deleteDishInOrder(dishInOrderRecord!!)
                }
            } else {
                dishInOrderRecord!!.count--
                viewModelScope.launch(Dispatchers.IO) {
                    dishInOrderRepository.updateDishInOrder(dishInOrderRecord!!)
                }
            }
        }
    }

    fun changeStatus(id: Int): Boolean {
        var dishInOrderRecord: DishInOrder? = null
        dishInOrderList.forEach {
            if (it.dishId == id) {
                dishInOrderRecord = it
                return@forEach
            }
        }
        try {
            if (dishInOrderRecord!!.statusId != 4) {
                dishInOrderRecord!!.statusId++
                viewModelScope.launch(Dispatchers.IO) {
                    dishInOrderRepository.updateDishInOrder(dishInOrderRecord!!)
                }
                return true
            }
            viewModelScope.launch(Dispatchers.IO) {
                dishInOrderRepository.updateDishInOrder(dishInOrderRecord!!)
            }
        } catch (_: NullPointerException) {
        }
        return false
    }

    fun checkOrderForDelete() {
        if (order!!.peopleCount == 0 && order?.tableId == 0) {
            viewModelScope.launch(Dispatchers.IO) {
                orderRepository.deleteOrder(order!!)
            }
        }
    }

    fun changePeopleCount(text: String): Boolean {
        var newCount: Int
        if (text != "") {
            newCount = text.toInt()
        } else {
            newCount = 0
        }
        if (newCount <= maxPeopleCount) {
            order!!.peopleCount = newCount
            viewModelScope.launch(Dispatchers.IO) {
                orderRepository.updateOrder(order!!)
            }
            return true
        }

        return false
    }

    fun changeTableNumber(text: String): Boolean {
        var newNumber: Int
        if (text != "") {
            newNumber = text.toInt()
        } else {
            newNumber = 0
        }

        if (newNumber <= maxTableNumber) {
            order!!.tableId = newNumber
            viewModelScope.launch(Dispatchers.IO) {
                orderRepository.updateOrder(order!!)
            }
            return true
        }

        return false
    }
}