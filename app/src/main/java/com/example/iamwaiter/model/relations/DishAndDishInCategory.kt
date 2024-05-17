package com.example.iamwaiter.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.model.entities.Dish
import com.example.iamwaiter.model.entities.DishInCategory

data class DishAndDishInCategory (
    @Embedded val dish: Dish,
    @Relation(
        parentColumn = "id",
        entityColumn = "dishId"
    )
    val dishInCategory: DishInCategory
) {}