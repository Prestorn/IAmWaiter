package com.example.iamwaiter.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.iamwaiter.data.Category
import com.example.iamwaiter.data.DishInCategory

data class CategoryAndDishInCategory (
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val dishInCategory: DishInCategory
) {}