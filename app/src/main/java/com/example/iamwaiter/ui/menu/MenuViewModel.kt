package com.example.iamwaiter.ui.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.iamwaiter.model.DataBase
import com.example.iamwaiter.model.entities.Category
import com.example.iamwaiter.model.repositories.CategoryRepository

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    val categoryDao = DataBase.getDatabase(application).categoryDao()
    val categoryRepository = CategoryRepository(categoryDao)

    var categoryList: LiveData<List<Category>> = categoryRepository.getAllCategories()

    val namesList = mutableListOf<String>()
    val idList = mutableListOf<Int>()

    fun fillLists(categoryList: List<Category>) {
        clearLists()
        categoryList.forEach {
            namesList.add(it.description)
            idList.add(it.id)
        }
    }

    fun clearLists() {
        namesList.clear()
        idList.clear()
    }
}