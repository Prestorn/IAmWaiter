package com.example.iamwaiter.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.model.entities.Category
import com.example.iamwaiter.model.entities.DishInCategory

data class CategoryAndDishInCategory (
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val dishInCategory: DishInCategory
) {}