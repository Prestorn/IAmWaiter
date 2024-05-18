package com.example.iamwaiter.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.DishStatus

@Dao
interface DishStatusDao {
    @Query("SELECT * FROM dishStatus")
    fun getAll():List<DishStatus>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dishStatus: DishStatus)

    @Delete
    fun delete(dishStatus: DishStatus)

    @Query("DELETE FROM dishStatus")
    fun deleteAll()

    @Update
    fun update(dishStatus: DishStatus)
}