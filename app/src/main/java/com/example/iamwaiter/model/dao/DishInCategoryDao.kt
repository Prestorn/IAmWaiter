package com.example.iamwaiter.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.DishInCategory

@Dao
interface DishInCategoryDao {
    @Query("SELECT * FROM dishInCategory")
    fun getAll():List<DishInCategory>

    @Query("SELECT * FROM dishInCategory WHERE categoryId = :id ORDER BY dishId")
    fun getAllDishesInCategoryLiveData(id: Int): LiveData<DishInCategory>

    @Query("SELECT * FROM dishInCategory WHERE categoryId = :id ORDER BY dishId")
    fun getAllDishesInCategory(id: Int): DishInCategory

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dishInCategory: DishInCategory)

    @Delete
    fun delete(dishInCategory: DishInCategory)

    @Query("DELETE FROM dishInCategory")
    fun deleteAll()

    @Update
    fun update(dishInCategory: DishInCategory)
}