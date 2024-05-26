package com.example.iamwaiter.ui.orderScreen

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
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

        viewModel.addDishEnable.value = true

        recyclerView = binding.dishesList
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.peopleCountEditText.addTextChangedListener { changePeopleCount() }
        binding.tableNumberEditText.addTextChangedListener { changeTableNumber() }
        binding.plusBackground.setOnClickListener{ addDishInOrder() }
        binding.backBackground.setOnClickListener{ goBack() }

        observeViewModel()
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

    private fun fillRecyclerView() {
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
        val dishViewModel = ViewModelProvider(activity as ViewModelStoreOwner)[DishViewModel::class]
        dishViewModel.dishId.value = id
        dishViewModel.navigateFromOrder.value = true
        findNavController().navigate(R.id.action_orderScreenFragment_to_dishFragment)
    }

    private fun addDishInOrder() {
        findNavController().navigate(R.id.action_orderScreenFragment_to_menuFromOrderFragment)
    }

    fun deleteDishFromOrder(id: Int) {
        val myDialog = AlertDialog.Builder(context).setMessage("Удалить блюдо?")
            .setNeutralButton("Отмена") { _, which -> deleteDishDialogListener(which, id) }
            .setPositiveButton("Удалить одну порцию") { _, which -> deleteDishDialogListener(which, id) }
            .setNegativeButton("Удалить все порции") { _, which -> deleteDishDialogListener(which, id) }
        myDialog.create()
        myDialog.show()
    }

    private fun deleteDishDialogListener(which: Int, id: Int) {
        when (which){
            DialogInterface.BUTTON_POSITIVE -> viewModel.deleteDishFromOrder(id, false)
            DialogInterface.BUTTON_NEGATIVE -> viewModel.deleteDishFromOrder(id, true)
            DialogInterface.BUTTON_NEUTRAL -> return
        }
        restart()
    }

    private fun goBack() {
        viewModel.addDishEnable.value = false
        viewModel.checkOrderForDelete()
        findNavController().navigate(R.id.action_orderScreenFragment_to_orderListFragment)
    }

    fun changeStatus(id: Int) {
        if (viewModel.changeStatus(id)) { restart() }
    }

    private fun restart() {
        findNavController().navigate(R.id.action_orderScreenFragment_to_orderListFragment)
        findNavController().navigate(R.id.action_orderListFragment_to_orderScreenFragment)
    }

    private fun changePeopleCount() {
        val text = binding.peopleCountEditText.text.toString()
        if (text != "") {
            viewModel.changePeopleCount(text.toInt())
        }
    }

    private fun changeTableNumber() {
        val text = binding.tableNumberEditText.text.toString()
        if (text != "") {
            viewModel.changeTableNumber(text.toInt())
        }
    }
}