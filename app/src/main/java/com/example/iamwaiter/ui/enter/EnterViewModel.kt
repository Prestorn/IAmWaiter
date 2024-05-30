package com.example.iamwaiter.ui.enter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.iamwaiter.model.Data
import com.example.iamwaiter.model.DataBase
import com.example.iamwaiter.model.entities.User
import com.example.iamwaiter.model.repositories.CategoryRepository
import com.example.iamwaiter.model.repositories.DishInCategoryRepository
import com.example.iamwaiter.model.repositories.DishInOrderRepository
import com.example.iamwaiter.model.repositories.DishRepository
import com.example.iamwaiter.model.repositories.DishStatusRepository
import com.example.iamwaiter.model.repositories.OrderRepository
import com.example.iamwaiter.model.repositories.OrderStatusRepository
import com.example.iamwaiter.model.repositories.TableRepository
import com.example.iamwaiter.model.repositories.TableStatusRepository
import com.example.iamwaiter.model.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnterViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var user: User

    private val userDao = DataBase.getDatabase(application).userDao()
    private val userRepository = UserRepository(userDao)

    private val categoryDao = DataBase.getDatabase(application).categoryDao()
    private val categoryRepository = CategoryRepository(categoryDao)

    private val dishDao = DataBase.getDatabase(application).dishDao()
    private val dishRepository = DishRepository(dishDao)

    private val dishInCategoryDao = DataBase.getDatabase(application).dishInCategoryDao()
    private val dishInCategoryRepository = DishInCategoryRepository(dishInCategoryDao)

    private val dishInOrderDao = DataBase.getDatabase(application).dishInOrderDao()
    private val dishInOrderRepository = DishInOrderRepository(dishInOrderDao)

    private val dishStatusDao = DataBase.getDatabase(application).dishStatusDao()
    private val dishStatusRepository = DishStatusRepository(dishStatusDao)

    private val orderDao = DataBase.getDatabase(application).orderDao()
    private val orderRepository = OrderRepository(orderDao)

    private val orderStatusDao = DataBase.getDatabase(application).orderStatusDao()
    private val orderStatusRepository = OrderStatusRepository(orderStatusDao)

    private val tableDao = DataBase.getDatabase(application).tableDao()
    private val tableRepository = TableRepository(tableDao)

    private val tableStatusDao = DataBase.getDatabase(application).tableStatusDao()
    private val tableStatusRepository = TableStatusRepository(tableStatusDao)

    private var usersList = listOf<User>()

    init {
        //clearDataBase()
        checkDataBaseFilling()
    }


    fun checkUserLogin(login: String, password: String): Boolean {
        updateUsersList()
        usersList.forEach { user ->
            if (user.login == login && user.password == password) {
                this.user = user
                return true
            }
        }
        return false
    }



    fun updateUsersList() {
        viewModelScope.launch(Dispatchers.IO) {
            //usersList = userRepository.getAllUsers()
            usersList = userRepository.getAllUsers()
        }
    }

    private fun checkDataBaseFilling() {
        viewModelScope.launch(Dispatchers.IO) {
            val userList = userRepository.getAllUsers()
            if (userList.isEmpty()){
                fillDataBase()
            }
        }
    }

    private fun fillDataBase() {
        Data.users.forEach{userRepository.addUser(it)}
        Data.tableStatuses.forEach{tableStatusRepository.addTableStatus(it)}
        Data.tables.forEach{tableRepository.addTable(it)}
        Data.orderStatuses.forEach{orderStatusRepository.addOrderStatus(it)}
        Data.orders.forEach{orderRepository.addOrder(it)}
        Data.categories.forEach{categoryRepository.addCategory(it)}
        Data.dishesInCategories.forEach{dishInCategoryRepository.addDishInCategory(it)}
        Data.dishes.forEach{dishRepository.addDish(it)}
        Data.dishStatuses.forEach{dishStatusRepository.addDishStatus(it)}
        Data.dishesInOrder.forEach{dishInOrderRepository.addDishInOrder(it)}
    }

    private fun clearDataBase() {
        viewModelScope.launch (Dispatchers.IO) {
            dishInCategoryRepository.deleteAllDishesInCategories()
            categoryRepository.deleteAllCategories()
            dishInOrderRepository.deleteAllDishesInOrders()
            dishStatusRepository.deleteAllDishStatuses()
            dishRepository.deleteAllDishes()
            orderStatusRepository.deleteAllOrderStatuses()
            tableStatusRepository.deleteAllTableStatuses()
            tableRepository.deleteAllTables()
            orderRepository.deleteAllOrders()
            userRepository.deleteAllUsers()
        }
    }

}