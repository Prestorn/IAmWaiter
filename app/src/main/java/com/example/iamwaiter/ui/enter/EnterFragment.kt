package com.example.iamwaiter.ui.enter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.iamwaiter.databinding.FragmentEnterBinding
import com.example.iamwaiter.R

class EnterFragment : Fragment() {

    lateinit var binding: FragmentEnterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener(){
            findNavController().navigate(R.id.action_enterFragment_to_orderListFragment)
        }
    }
}