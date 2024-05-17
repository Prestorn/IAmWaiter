package com.example.iamwaiter.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.data.Table
import com.example.iamwaiter.data.TableStatus


data class TableStatusAndTable (
    @Embedded val tableStatus: TableStatus,
    @Relation(
        parentColumn = "id",
        entityColumn = "statusId"
    )
    val table: Table
) {}