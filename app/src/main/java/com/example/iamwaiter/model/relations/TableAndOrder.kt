package com.example.iamwaiter.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.model.entities.Order
import com.example.iamwaiter.model.entities.Table


data class TableAndOrder (
    @Embedded val table: Table,
    @Relation(
        parentColumn = "id",
        entityColumn = "tableId"
    )
    val order: Order
) {}