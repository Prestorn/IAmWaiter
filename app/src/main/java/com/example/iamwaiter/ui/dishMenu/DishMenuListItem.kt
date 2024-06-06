package com.example.iamwaiter.ui.dishMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iamwaiter.R

class DishMenuListItem(
    private val namesList: List<String>,
    private val costList: List<Int>,
    private val weightList: List<Int>,
    private val idList: List<Int>,
    private val parentFragment: DishMenuFragment
): RecyclerView.Adapter<DishMenuListItem.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTextView = itemView.findViewById<TextView>(R.id.dishInCategoryNameTextView)
        val costTextView = itemView.findViewById<TextView>(R.id.costDishInCategoryTextView)
        val weightTextView = itemView.findViewById<TextView>(R.id.weightValueDishInCategoryTextView)
        val dishBackground = itemView.findViewById<View>(R.id.dishInCategoryBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dish_in_category_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameTextView.text = namesList[position]
        holder.costTextView.text = costList[position].toString()
        holder.weightTextView.text = weightList[position].toString()
        holder.dishBackground.setOnClickListener {
            parentFragment.onDishSelected(idList[position])
        }
    }

    override fun getItemCount(): Int {
        return namesList.size
    }
}