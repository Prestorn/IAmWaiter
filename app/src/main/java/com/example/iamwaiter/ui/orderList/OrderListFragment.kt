package com.example.iamwaiter.ui.orderList

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iamwaiter.databinding.FragmentOrderListBinding
import com.example.iamwaiter.R
import com.example.iamwaiter.ui.orderScreen.OrderScreenViewModel
import java.lang.NullPointerException

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
        observeViewModel()
        viewModel.updateOrderList()


        recyclerView = binding.orderList
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.plusBackground.setOnClickListener { createOrder() }

        /*
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_orderListFragment_to_enterFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        */
    }

    private fun observeViewModel(){

        viewModel.user.observe(viewLifecycleOwner) {
            viewModel.updateOrderList()
        }

        viewModel.orderList.observe(viewLifecycleOwner) {
            Log.d("orderList.observe", "$it,\n ${viewModel.orderListValue}")
            viewModel.updateLists()
            fillRecyclerView()
            if (it.isNotEmpty()) {
                viewModel.newOrder = it[it.size - 1]
                checkNewOrder()
            }
        }
    }
    private fun fillRecyclerView() {
        val peopleCountList = viewModel.peopleCountList
        val tableNumberList = viewModel.tableNumberList
        val costList = viewModel.costList
        val idList = viewModel.idList
        recyclerView.adapter = OrderListRecyclerViewItem(peopleCountList, tableNumberList, costList, idList, this)
    }

    fun onOrderSelected(id:Int){
        viewModel.selectedOrderId.value = id
        ViewModelProvider(activity as ViewModelStoreOwner)[OrderScreenViewModel::class].orderId.value = id
        findNavController().navigate(R.id.action_orderListFragment_to_orderScreenFragment)
    }

    fun deleteOrder(id: Int) {
        val myDialog = AlertDialog.Builder(context)
            .setMessage("Удалить заказ?")
            .setNegativeButton("Нет") { _, _ -> }
            .setPositiveButton("Да") { _, _ -> viewModel.deleteOrder(id) }
        myDialog.create()
        myDialog.show()
    }


    private fun createOrder() {
        try {
            viewModel.createOrder()
            Log.i("newOrderId", "${viewModel.newOrder}")
        } catch (e: Exception) {
            Log.e("createOrder", "$e")
        }
    }

    private fun checkNewOrder() {
        try {
            if (viewModel.newOrder!!.peopleCount == 0 && viewModel.newOrder!!.tableId == 0) {
                ViewModelProvider(activity as ViewModelStoreOwner)[OrderScreenViewModel::class].orderId.value = viewModel.newOrder!!.id
                findNavController().navigate(R.id.action_orderListFragment_to_orderScreenFragment)
            }
        } catch (e: NullPointerException) {
            Log.e("checkNewOrder", "$e")
        }
    }
}