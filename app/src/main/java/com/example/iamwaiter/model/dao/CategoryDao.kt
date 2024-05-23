package com.example.iamwaiter.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(category: Category)

    @Delete
    fun delete(category: Category)

    @Query("DELETE FROM category")
    fun deleteAll()

    @Update
    fun update(category: Category)
}