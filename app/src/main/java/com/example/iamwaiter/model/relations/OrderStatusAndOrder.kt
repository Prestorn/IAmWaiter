package com.example.iamwaiter.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.model.entities.Order
import com.example.iamwaiter.model.entities.OrderStatus

data class OrderStatusAndOrder (
    @Embedded val orderStatus: OrderStatus,
    @Relation(
        parentColumn = "id",
        entityColumn = "statusId"
    )
    val order: Order
) {}