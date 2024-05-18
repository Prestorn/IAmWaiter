package com.example.iamwaiter.model.repositories

import com.example.iamwaiter.model.dao.TableDao
import com.example.iamwaiter.model.entities.Table

class TableRepository(private val dao: TableDao) {
    fun getAllTables(): List<Table> {
        return dao.getAll()
    }

    fun addTable(table: Table){
        dao.insert(table)
    }

    fun deleteTable(table: Table){
        dao.delete(table)
    }

    fun deleteAllTables(){
        dao.deleteAll()
    }

    fun updateTable(table: Table){
        dao.update(table)
    }
}