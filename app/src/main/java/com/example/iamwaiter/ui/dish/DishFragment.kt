package com.example.iamwaiter.ui.dish

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.example.iamwaiter.R
import com.example.iamwaiter.databinding.FragmentDishBinding

class DishFragment : Fragment() {

    lateinit var binding: FragmentDishBinding
    lateinit var viewModel: DishViewModel

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
        observeViewModel()

        binding.backBackground.setOnClickListener{ goBack() }
        binding.descriptionTextView.movementMethod = ScrollingMovementMethod()
    }

    private fun observeViewModel() {

        viewModel.dishId.observe(viewLifecycleOwner) {
            viewModel.dishIdValue = it
            viewModel.updateDish()
        }

        viewModel.dishLiveData.observe(viewLifecycleOwner) {
            val dish = viewModel.dish
            Log.i("dish", "$it \n ${dish} \n ${R.mipmap.dish1} \n" +
                    " ${R.mipmap.dish2} \n" +
                    " ${R.mipmap.dish3}" )
            val imageId = requireActivity().resources.getIdentifier("dish${dish.id}", "mipmap", requireActivity().packageName)
            Log.i("imageId", "$imageId")
            binding.dishImage.setImageResource(imageId)
            binding.dishNameTextView.text = dish.name
            binding.descriptionTextView.text = dish.description
            binding.costTextView.text = dish.cost.toString()
            binding.weightTextView.text = dish.weight.toString()
        }
    }

    private fun goBack() {
        findNavController().navigate(R.id.action_dishFragment_to_orderScreenFragment)
    }

}