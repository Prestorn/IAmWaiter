package com.example.iamwaiter.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.model.entities.Dish
import com.example.iamwaiter.model.entities.DishInOrder

data class DishAndDishInOrder (
    @Embedded val dish: Dish,
    @Relation(
        parentColumn = "id",
        entityColumn = "dishId"
    )
    val dishInOrder: DishInOrder
) {}