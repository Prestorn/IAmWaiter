package com.example.iamwaiter.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.Table

@Dao
interface TableDao {
    @Query("SELECT * FROM `table`")
    fun getAll():List<Table>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(table: Table)

    @Delete
    fun delete(table: Table)

    @Query("DELETE FROM `table`")
    fun deleteAll()

    @Update
    fun update(table: Table)
}