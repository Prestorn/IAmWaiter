package com.example.iamwaiter.ui.dishMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.iamwaiter.R
import com.example.iamwaiter.databinding.FragmentDishMenuBinding
import com.example.iamwaiter.ui.dish.DishViewModel

class DishMenuFragment : Fragment() {

    lateinit var binding: FragmentDishMenuBinding
    lateinit var viewModel: DishMenuViewModel
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDishMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val provider = ViewModelProvider(activity as ViewModelStoreOwner)
        viewModel = provider[DishMenuViewModel::class]

        recyclerView = binding.dishList
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.backBackground.setOnClickListener { goBack() }

        viewModel.updateDishList()

        observeViewModel()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                goBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    fun observeViewModel() {

        viewModel.categoryId.observe(viewLifecycleOwner) {
            viewModel.categoryIdValue = it
            viewModel.updateDishList()
        }

        viewModel.dishListLiveData.observe(viewLifecycleOwner) {
            viewModel.updateLists()
            updateRecyclerView()
        }

        viewModel.navigateFromMenu.observe(viewLifecycleOwner) {
            viewModel.navigateFromOrder = !it
        }
    }

    private fun updateRecyclerView() {
        val namesList = viewModel.namesList
        val costList = viewModel.costList
        val weightList = viewModel.weightList
        val idList = viewModel.idList
        recyclerView.adapter = DishMenuListItem(namesList, costList, weightList, idList, this)
    }

    fun onDishSelected(id: Int) {
        val dishViewModel = ViewModelProvider(activity as ViewModelStoreOwner)[DishViewModel::class]
        dishViewModel.dishId.value = id
        dishViewModel.navigateFromOrder.value = false
        findNavController().navigate(R.id.action_dishMenuFragment_to_dishFragment)
    }

    private fun goBack() {
        viewModel.searchUsed.value = false
        if (viewModel.navigateFromOrder){
            findNavController().navigate(R.id.action_dishMenuFragment_to_menuFromOrderFragment)
        } else {
            findNavController().navigate(R.id.action_dishMenuFragment_to_menuFragment)
        }
    }
}