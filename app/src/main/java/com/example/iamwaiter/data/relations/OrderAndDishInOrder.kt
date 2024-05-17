package com.example.iamwaiter.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.data.Order

data class OrderAndDishInOrder (
    @Embedded val order: Order,
    @Relation(
        parentColumn = "id",
        entityColumn = "orderId"
    )
    val dishInOrder: OrderAndDishInOrder
) {}