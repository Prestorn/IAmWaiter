package com.example.iamwaiter.ui.orderList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iamwaiter.databinding.FragmentOrderListBinding

class OrderListFragment : Fragment() {
    lateinit var binding: FragmentOrderListBinding
    lateinit var viewModel: OrderListViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val provider = ViewModelProvider((activity as ViewModelStoreOwner))
        viewModel = provider.get(OrderListViewModel::class)
        viewModel.updateOrderList()

        recyclerView = binding.orderList
        recyclerView.layoutManager = LinearLayoutManager(context)

        observeViewModel()
    }
    fun observeViewModel(){
        val personCountList = ArrayList<Int>()
        val tableNumberList = ArrayList<Int>()
        val costList = ArrayList<Int>()

        viewModel.orderList.observe(viewLifecycleOwner, Observer {
            it.forEach { order -> personCountList.add(order.id)
            tableNumberList.add(order.tableId)
            costList.add(order.sum)}

            recyclerView.adapter = OrderListRecyclerViewItem(personCountList, tableNumberList, costList)
        })
    }
}