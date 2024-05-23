package com.example.iamwaiter.model.repositories

import androidx.lifecycle.LiveData
import com.example.iamwaiter.model.dao.CategoryDao
import com.example.iamwaiter.model.entities.Category

class CategoryRepository(private val dao: CategoryDao) {

    fun getAllCategories(): LiveData<List<Category>> {
        return dao.getAll()
    }

    fun addCategory(category: Category) {
        dao.insert(category)
    }

    fun deleteCategory(category: Category) {
        dao.delete(category)
    }

    fun deleteAllCategories(){
        dao.deleteAll()
    }

    fun updateCategory(category: Category) {
        dao.update(category)
    }

}