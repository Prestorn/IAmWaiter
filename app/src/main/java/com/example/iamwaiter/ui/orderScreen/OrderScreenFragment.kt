package com.example.iamwaiter.ui.orderScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iamwaiter.R
import com.example.iamwaiter.databinding.FragmentOrderScreenBinding
import com.example.iamwaiter.ui.dish.DishViewModel

class OrderScreenFragment : Fragment() {

    lateinit var binding: FragmentOrderScreenBinding
    lateinit var viewModel:OrderScreenViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val provider = ViewModelProvider(activity as ViewModelStoreOwner)
        viewModel = provider[OrderScreenViewModel::class]

        recyclerView = binding.dishesList
        recyclerView.layoutManager = LinearLayoutManager(context)

        observeViewModel()
        binding.plusBackground.setOnClickListener{ addDishInOrder() }
        binding.backBackground.setOnClickListener{ returnToOrderList() }
    }

    override fun onResume() {
        super.onResume()
        //viewModel.updateDishInOrderList()
    }

    private fun observeViewModel() {

        viewModel.orderId.observe(viewLifecycleOwner) {
            viewModel.updateOrder(it)
        }

        viewModel.dishListLiveData.observe(viewLifecycleOwner) {
            viewModel.updateLists()
            fillRecyclerView()
        }

    }

    fun fillRecyclerView() {
        val countList: ArrayList<Int> = viewModel.countList
        val namesList: ArrayList<String> = viewModel.namesList
        val costList: ArrayList<Int> = viewModel.costList
        val statusesList: ArrayList<Int> = viewModel.statusesList
        val idList: ArrayList<Int> = viewModel.idList
        binding.peopleCountEditText.setText(viewModel.peopleCount.toString())
        binding.tableNumberEditText.setText(viewModel.tableNumber.toString())
        binding.costTextView.text = viewModel.orderCost.toString()
        recyclerView.adapter = DishInOrderRecyclerViewItem(countList, namesList, costList, statusesList, idList, this)

    }

    fun onDishSelected(id: Int) {
        ViewModelProvider(activity as ViewModelStoreOwner)[DishViewModel::class].dishIdValue = id
        findNavController().navigate(R.id.action_orderScreenFragment_to_dishFragment)
    }

    fun addDishInOrder() {

    }

    fun returnToOrderList() {
        findNavController().navigate(R.id.action_orderScreenFragment_to_orderListFragment)
    }
}