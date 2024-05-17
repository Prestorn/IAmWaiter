package com.example.iamwaiter.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.data.Order
import com.example.iamwaiter.data.OrderStatus

data class OrderStatusAndOrder (
    @Embedded val orderStatus: OrderStatus,
    @Relation(
        parentColumn = "id",
        entityColumn = "statusId"
    )
    val order: Order
) {}