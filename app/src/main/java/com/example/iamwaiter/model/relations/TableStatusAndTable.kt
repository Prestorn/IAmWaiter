package com.example.iamwaiter.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.model.entities.Table
import com.example.iamwaiter.model.entities.TableStatus


data class TableStatusAndTable (
    @Embedded val tableStatus: TableStatus,
    @Relation(
        parentColumn = "id",
        entityColumn = "statusId"
    )
    val table: Table
) {}