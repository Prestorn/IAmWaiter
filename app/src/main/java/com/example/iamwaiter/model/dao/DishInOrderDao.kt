package com.example.iamwaiter.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.DishInOrder

@Dao
interface DishInOrderDao {
    @Query("SELECT * FROM dishInOrder")
    fun getAll(): LiveData<List<DishInOrder>>

    @Query("SELECT * FROM dishInOrder WHERE orderId = :id ORDER BY dishId")
    fun getAllByOrderId(id: Int): LiveData<List<DishInOrder>>

    @Query("SELECT * FROM dishInOrder WHERE orderId = :id ORDER BY dishId")
    fun getAllValueByOrderId(id: Int): List<DishInOrder>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dishInOrder: DishInOrder)

    @Delete
    fun delete(dishInOrder: DishInOrder)

    @Query("DELETE FROM dishInOrder")
    fun deleteAll()

    @Update
    fun update(dishInOrder: DishInOrder)
}