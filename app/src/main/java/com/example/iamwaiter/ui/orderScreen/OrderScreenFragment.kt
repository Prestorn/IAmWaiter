package com.example.iamwaiter.ui.orderScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.iamwaiter.databinding.FragmentOrderScreenBinding
import com.example.iamwaiter.ui.orderList.OrderListViewModel

class OrderScreenFragment : Fragment() {

    lateinit var binding: FragmentOrderScreenBinding
    lateinit var viewModel:OrderScreenViewModel

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
        binding.textView.text = viewModel.orderId.value.toString()
    }
}