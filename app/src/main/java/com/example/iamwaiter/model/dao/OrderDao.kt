package com.example.iamwaiter.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.Order

@Dao
interface OrderDao {

    @Query("SELECT * FROM `order`")
    fun getAll(): LiveData<List<Order>>

    @Query("SELECT * FROM `order` WHERE id = :id")
    fun getOrderById(id: Int): Order

    @Query("SELECT * FROM `order` WHERE userId = :id")
    fun getOrdersByUserId(id: Int): LiveData<List<Order>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(order: Order)

    @Delete
    fun delete(order: Order)

    @Query("DELETE FROM `order`")
    fun deleteAll()

    @Update
    fun update(order: Order)
}