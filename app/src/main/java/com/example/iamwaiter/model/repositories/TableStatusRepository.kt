package com.example.iamwaiter.model.repositories

import com.example.iamwaiter.model.dao.TableStatusDao
import com.example.iamwaiter.model.entities.TableStatus

class TableStatusRepository(private val dao: TableStatusDao) {
    fun getAllTableStatuses(): List<TableStatus> {
        return dao.getAll()
    }

    fun addTableStatus(tableStatus: TableStatus){
        dao.insert(tableStatus)
    }

    fun deleteTableStatus(tableStatus: TableStatus){
        dao.delete(tableStatus)
    }

    fun deleteAllTableStatuses(){
        dao.deleteAll()
    }

    fun updateTableStatus(tableStatus: TableStatus){
        dao.update(tableStatus)
    }
}