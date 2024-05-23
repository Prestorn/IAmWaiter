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
import com.example.iamwaiter.databinding.FragmentEnterBinding
import com.example.iamwaiter.databinding.FragmentMenuBinding
import com.example.iamwaiter.ui.dishMenu.DishMenuViewModel
import com.example.iamwaiter.ui.orderScreen.OrderScreenViewModel

class MenuFragment : Fragment() {

    lateinit var binding: FragmentMenuBinding
    lateinit var viewModel: MenuViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val provider = ViewModelProvider(activity as ViewModelStoreOwner)
        viewModel = provider[MenuViewModel::class]

        recyclerView = binding.categoryList
        recyclerView.layoutManager = LinearLayoutManager(context)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.categoryList.observe(viewLifecycleOwner) {
            viewModel.fillLists(it)
            recyclerView.adapter = CategoryListItem(viewModel.namesList, viewModel.idList, this)
        }
    }

    fun onCategorySelected(id: Int) {
        val dishMenuViewModel = ViewModelProvider(activity as ViewModelStoreOwner)[DishMenuViewModel::class]
        dishMenuViewModel.categoryId.value = id
        dishMenuViewModel.navigateFromMenu.value = true
        findNavController().navigate(R.id.action_menuFragment_to_dishMenuFragment)
    }
}