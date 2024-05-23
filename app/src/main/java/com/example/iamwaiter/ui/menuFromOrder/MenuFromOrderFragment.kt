package com.example.iamwaiter.ui.menu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iamwaiter.R
import com.example.iamwaiter.databinding.FragmentMenuFromOrderBinding
import com.example.iamwaiter.ui.orderScreen.OrderScreenViewModel

class MenuFromOrderFragment : Fragment() {

    lateinit var binding: FragmentMenuFromOrderBinding
    lateinit var viewModel: MenuFromOrderViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuFromOrderBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val presenter = ViewModelProvider(activity as ViewModelStoreOwner)
        viewModel = presenter[MenuFromOrderViewModel::class]

        recyclerView = binding.categoryList
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.backBackground.setOnClickListener { goBack() }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.categoryList.observe(viewLifecycleOwner) {
            viewModel.fillLists(it)
            recyclerView.adapter = CategoryListItemFromOrder(viewModel.namesList, viewModel.idList, this)
        }
    }

    fun onCategorySelected(id: Int) {
        Toast.makeText(context, "$id", Toast.LENGTH_SHORT).show()
    }

    private fun goBack() {
        if (ViewModelProvider(activity as ViewModelStoreOwner)[OrderScreenViewModel::class].addDishInOrderEnable.value!!) {
            findNavController().navigate(R.id.action_menuFromOrderFragment_to_orderScreenFragment)
        }
    }
}