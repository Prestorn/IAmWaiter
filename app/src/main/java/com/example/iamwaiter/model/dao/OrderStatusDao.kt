package com.example.iamwaiter.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.OrderStatus

@Dao
interface OrderStatusDao {
    @Query("SELECT * FROM orderStatus")
    fun getAll():List<OrderStatus>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(orderStatus: OrderStatus)

    @Delete
    fun delete(orderStatus: OrderStatus)

    @Query("DELETE FROM orderStatus")
    fun deleteAll()

    @Update
    fun update(orderStatus: OrderStatus)
}