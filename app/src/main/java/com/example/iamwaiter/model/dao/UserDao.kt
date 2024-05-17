package com.example.iamwaiter.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.iamwaiter.model.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAllUsers():List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user")
    fun deleteAllUsers()

    @Update
    fun update(user: User)
}