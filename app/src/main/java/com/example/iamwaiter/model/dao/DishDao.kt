package com.example.iamwaiter.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.Dish

@Dao
interface DishDao {
    @Query("SELECT * FROM dish")
    fun getAll(): LiveData<List<Dish>>

    @Query("SELECT * FROM dish WHERE id = :id")
    fun getDishById(id: Int): LiveData<Dish>

    @Query("SELECT * FROM dish WHERE id = :id")
    fun getDishValueById(id: Int): Dish

    @Query("SELECT * FROM dish WHERE id IN (" +
            "SELECT dishId FROM dishInOrder WHERE orderId = :id)")
    fun getDishListByOrderId(id: Int): LiveData<List<Dish>>

    @Query("SELECT * FROM dish WHERE id IN (" +
            "SELECT dishId FROM dishInOrder WHERE orderId = :id)")
    fun getDishListValueByOrderId(id: Int): List<Dish>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dish: Dish)

    @Delete
    fun delete(dish: Dish)

    @Query("DELETE FROM dish")
    fun deleteAll()

    @Update
    fun update(dish: Dish)
}