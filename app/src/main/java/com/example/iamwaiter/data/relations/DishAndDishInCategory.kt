package com.example.iamwaiter.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.data.Dish
import com.example.iamwaiter.data.DishInCategory

data class DishAndDishInCategory (
    @Embedded val dish: Dish,
    @Relation(
        parentColumn = "id",
        entityColumn = "dishId"
    )
    val dishInCategory: DishInCategory
) {}