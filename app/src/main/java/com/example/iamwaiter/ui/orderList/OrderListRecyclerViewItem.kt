package com.example.iamwaiter.ui.orderList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iamwaiter.R

class OrderListRecyclerViewItem(
    private val personCount: List<Int>,
    private val tableNumber: List<Int>,
    private val cost:List<Int>) : RecyclerView.Adapter<OrderListRecyclerViewItem.MyViewHolder>() {

        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val personCountTextView:TextView = itemView.findViewById(R.id.textViewPersonCount)
            val tableNumberTextView:TextView = itemView.findViewById(R.id.textViewTableNumber)
            val costTextView:TextView = itemView.findViewById(R.id.textViewCost)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.personCountTextView.text = personCount[position].toString()
        holder.tableNumberTextView.text = tableNumber[position].toString()
        holder.costTextView.text = cost[position].toString()
    }

    override fun getItemCount(): Int {
        return personCount.size
    }
}
