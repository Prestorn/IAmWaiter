package com.example.iamwaiter.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.iamwaiter.model.dao.OrderDao
import com.example.iamwaiter.model.dao.UserDao
import com.example.iamwaiter.model.entities.Order
import com.example.iamwaiter.model.entities.User

@Database(entities = [User::class, Order::class], version = 3, exportSchema = false)
abstract class DataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao

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