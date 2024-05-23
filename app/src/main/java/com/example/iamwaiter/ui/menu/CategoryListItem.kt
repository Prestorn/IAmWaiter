package com.example.iamwaiter.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iamwaiter.R

class CategoryListItem(
    private val namesList: List<String>,
    private val idList: List<Int>,
    private val parentFragment: MenuFragment
): RecyclerView.Adapter<CategoryListItem.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val namesListTextView = itemView.findViewById<TextView>(R.id.categoryNameTextView)
        val listItemBackground = itemView.findViewById<View>(R.id.categoryListItemBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dish_category_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.namesListTextView.text = namesList[position]
        holder.listItemBackground.setOnClickListener {
            parentFragment.onCategorySelected(idList[position])
        }
    }

    override fun getItemCount(): Int {
        return namesList.size
    }
}