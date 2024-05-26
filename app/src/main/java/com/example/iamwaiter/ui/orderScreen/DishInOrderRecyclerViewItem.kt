package com.example.iamwaiter.ui.orderScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iamwaiter.R

class DishInOrderRecyclerViewItem(
    private val countList: List<Int>,
    private val namesList: List<String>,
    private val costList: List<Int>,
    private val statusesList: List<Int>,
    private val idList: List<Int>,
    private val parentFragment: OrderScreenFragment
): RecyclerView.Adapter<DishInOrderRecyclerViewItem.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val countListTextView = itemView.findViewById<TextView>(R.id.dishInOrderListItemCountTextView)
        val nameTextView = itemView.findViewById<TextView>(R.id.dishInOrderListItemNameTextView)
        val costTextView = itemView.findViewById<TextView>(R.id.dishInOrderListItemCostTextView)
        val statusImageView = itemView.findViewById<ImageView>(R.id.dishStatusImageView)
        val listItemBackground = itemView.findViewById<View>(R.id.dishInOrderListItemBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.dish_in_order_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.countListTextView.text = "${countList[position]}x"
        holder.nameTextView.text = namesList[position]
        holder.costTextView.text = costList[position].toString()
        when (statusesList[position]){
            1 -> holder.statusImageView.setImageResource(R.drawable.ic_dish_status_1)
            2 -> holder.statusImageView.setImageResource(R.drawable.ic_dish_status_2)
            3 -> holder.statusImageView.setImageResource(R.drawable.ic_dish_status_3)
            4 -> holder.statusImageView.setImageResource(R.drawable.ic_dish_status_4)
            5 -> holder.statusImageView.setImageResource(R.drawable.ic_dish_status_5)
        }
        holder.listItemBackground.setOnClickListener{
            parentFragment.onDishSelected(idList[position])
        }
        holder.listItemBackground.setOnLongClickListener {
            parentFragment.deleteDishFromOrder(idList[position])
            return@setOnLongClickListener true
        }
        holder.statusImageView.setOnClickListener{
            parentFragment.changeStatus(idList[position])
        }
    }

    override fun getItemCount(): Int {
        return countList.size
    }
}