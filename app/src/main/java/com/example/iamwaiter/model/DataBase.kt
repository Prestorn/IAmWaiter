package com.example.iamwaiter.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.iamwaiter.model.dao.CategoryDao
import com.example.iamwaiter.model.dao.DishDao
import com.example.iamwaiter.model.dao.DishInCategoryDao
import com.example.iamwaiter.model.dao.DishInOrderDao
import com.example.iamwaiter.model.dao.DishStatusDao
import com.example.iamwaiter.model.dao.OrderDao
import com.example.iamwaiter.model.dao.OrderStatusDao
import com.example.iamwaiter.model.dao.TableDao
import com.example.iamwaiter.model.dao.TableStatusDao
import com.example.iamwaiter.model.dao.UserDao
import com.example.iamwaiter.model.entities.Category
import com.example.iamwaiter.model.entities.Dish
import com.example.iamwaiter.model.entities.DishInCategory
import com.example.iamwaiter.model.entities.DishInOrder
import com.example.iamwaiter.model.entities.DishStatus
import com.example.iamwaiter.model.entities.Order
import com.example.iamwaiter.model.entities.OrderStatus
import com.example.iamwaiter.model.entities.Table
import com.example.iamwaiter.model.entities.TableStatus
import com.example.iamwaiter.model.entities.User

@Database(entities = [Category::class,
                     Dish::class,
                     DishInCategory::class,
                     DishInOrder::class,
                     DishStatus::class,
                     Order::class,
                     OrderStatus::class,
                     Table::class,
                     TableStatus::class,
                     User::class],
                        version = 4, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun dishDao(): DishDao
    abstract fun dishInCategory(): DishInCategoryDao
    abstract fun dishInOrderDao(): DishInOrderDao
    abstract fun dishStatusDao(): DishStatusDao
    abstract fun orderDao(): OrderDao
    abstract fun orderStatusDao(): OrderStatusDao
    abstract fun tableDao(): TableDao
    abstract fun tableStatusDao(): TableStatusDao
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "IAmWaiter_DataBase"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}