package com.example.iamwaiter.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.Order
import com.example.iamwaiter.model.entities.User

@Dao
interface OrderDao {

    @Query("SELECT * FROM `order`")
    fun getAll(): List<Order>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(order: Order)

    @Delete
    fun delete(order: Order)

    @Query("DELETE FROM `order`")
    fun deleteAll()

    @Update
    fun update(order: Order)
}