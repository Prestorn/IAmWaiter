package com.example.iamwaiter.ui.dish

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.iamwaiter.R

class DishCountAdapter(private val context: Context, private val countList: List<String>): BaseAdapter() {

    override fun getCount(): Int {
        return countList.size
    }

    override fun getItem(position: Int): Any {
        return countList[position]
    }

    override fun getItemId(position: Int): Long {
        return (position + 1).toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.spinner_dish_count_item, parent, false)

        val textView = view.findViewById<TextView>(R.id.dishCountTextView)
        textView.text = countList[position]

        return view
    }
}