package com.example.iamwaiter.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.TableStatus

@Dao
interface TableStatusDao {
    @Query("SELECT * FROM `tableStatus`")
    fun getAll():List<TableStatus>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tableStatus: TableStatus)

    @Delete
    fun delete(tableStatus: TableStatus)

    @Query("DELETE FROM tableStatus")
    fun deleteAll()

    @Update
    fun update(tableStatus: TableStatus)
}