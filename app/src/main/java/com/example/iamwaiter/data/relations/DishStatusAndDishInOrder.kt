package com.example.iamwaiter.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.data.DishStatus

data class DishStatusAndDishInOrder (
    @Embedded val dishStatus: DishStatus,
    @Relation(
        parentColumn = "id",
        entityColumn = "statusId"
    )
    val dishInOrder: OrderAndDishInOrder
) {}