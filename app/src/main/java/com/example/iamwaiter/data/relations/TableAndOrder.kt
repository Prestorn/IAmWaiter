package com.example.iamwaiter.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.data.Order
import com.example.iamwaiter.data.Table


data class TableAndOrder (
    @Embedded val table: Table,
    @Relation(
        parentColumn = "id",
        entityColumn = "tableId"
    )
    val order: Order
) {}