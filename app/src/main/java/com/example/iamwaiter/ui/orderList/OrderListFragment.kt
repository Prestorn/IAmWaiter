package com.example.iamwaiter.ui.orderList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iamwaiter.databinding.FragmentOrderListBinding
import com.example.iamwaiter.R
import com.example.iamwaiter.ui.orderScreen.OrderScreenViewModel

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

        val provider = ViewModelProvider(activity as ViewModelStoreOwner)
        viewModel = provider[OrderListViewModel::class]
        viewModel.updateOrderList()

        recyclerView = binding.orderList
        recyclerView.layoutManager = LinearLayoutManager(context)

        observeViewModel()
    }
    private fun observeViewModel(){
        val peopleCountList = ArrayList<Int>()
        val tableNumberList = ArrayList<Int>()
        val costList = ArrayList<Int>()
        val idList = ArrayList<Int>()

        viewModel.orderList.observe(viewLifecycleOwner, Observer {
            peopleCountList.clear()
            tableNumberList.clear()
            costList.clear()
            idList.clear()
            it.forEach { order ->
                peopleCountList.add(order.peopleCount)
                tableNumberList.add(order.tableId)
                costList.add(order.cost)
                idList.add(order.id)
            }
            recyclerView.adapter = OrderListRecyclerViewItem(peopleCountList, tableNumberList, costList, idList, this)
        })
    }

    fun onOrderSelected(id:Int){
        viewModel.selectedOrderId.value = id
        ViewModelProvider(activity as ViewModelStoreOwner)[OrderScreenViewModel::class].orderId.value = id
        findNavController().navigate(R.id.action_orderListFragment_to_orderScreenFragment)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateOrderList()
    }
}