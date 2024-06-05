package com.example.iamwaiter.ui.dish


import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.example.iamwaiter.R
import com.example.iamwaiter.databinding.FragmentDishBinding
import com.example.iamwaiter.ui.orderScreen.OrderScreenViewModel


class DishFragment : Fragment() {

    lateinit var binding: FragmentDishBinding
    lateinit var viewModel: DishViewModel
    lateinit var spinner: Spinner
    var addDishEnable: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDishBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val provider = ViewModelProvider(activity as ViewModelStoreOwner)
        viewModel = provider[DishViewModel::class]

        binding.backBackground.setOnClickListener{ goBack() }
        binding.descriptionTextView.movementMethod = ScrollingMovementMethod()
        binding.plusBackground.setOnClickListener { addDishInOrder() }

        addDishEnable = provider[OrderScreenViewModel::class].addDishEnable.value

        observeViewModel()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                goBack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        createSpinner()
    }

    private fun observeViewModel() {

        viewModel.dishId.observe(viewLifecycleOwner) {
            viewModel.dishIdValue = it
            viewModel.updateDish()
        }

        viewModel.dishLiveData.observe(viewLifecycleOwner) {
            val dish = viewModel.dish
            val imageId = requireActivity().resources.getIdentifier("dish${dish.id}", "mipmap", requireActivity().packageName)
            binding.dishImage.setImageResource(imageId)
            binding.dishNameTextView.text = dish.name
            binding.descriptionTextView.text = dish.description
            binding.costTextView.text = dish.cost.toString()
            binding.weightTextView.text = dish.weight.toString()
        }

        viewModel.navigateFromOrder.observe(viewLifecycleOwner) {
            viewModel.navigateFromMenu = !it
            setAddVidibility()
        }
    }

    private fun goBack() {
        if (viewModel.navigateFromMenu) {
            findNavController().navigate(R.id.action_dishFragment_to_dishMenuFragment)
        } else {
            findNavController().navigate(R.id.action_dishFragment_to_orderScreenFragment)
        }
    }

    private fun setAddVidibility() {
        if (addDishEnable == true) {
            binding.plus.visibility = View.VISIBLE
            binding.plusBackground.visibility = View.VISIBLE
            binding.spinner.visibility = View.VISIBLE
        } else {
            binding.plus.visibility = View.INVISIBLE
            binding.plusBackground.visibility = View.INVISIBLE
            binding.spinner.visibility = View.INVISIBLE
        }
    }

    private fun addDishInOrder() {
        try {
            if (addDishEnable == true) {
                val dishCount: Int = spinner.selectedItem.toString().toInt()
                ViewModelProvider(activity as ViewModelStoreOwner)[OrderScreenViewModel::class].addDishInOrder(viewModel.dishIdValue, dishCount)
                Toast.makeText(context, "Блюдо добавлено", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_dishFragment_to_orderScreenFragment)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка добавления", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createSpinner() {
        if (addDishEnable == true) {
            spinner = binding.spinner
            val adapter = DishCountAdapter(requireContext(), listOf("1", "2", "3", "4", "5", "6", "7", "8", "9"))
            spinner.adapter = adapter
        }
    }
}