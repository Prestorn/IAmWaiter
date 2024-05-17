package com.example.iamwaiter.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.model.entities.DishStatus

data class DishStatusAndDishInOrder (
    @Embedded val dishStatus: DishStatus,
    @Relation(
        parentColumn = "id",
        entityColumn = "statusId"
    )
    val dishInOrder: OrderAndDishInOrder
) {}