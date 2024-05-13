package com.example.iamwaiter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.appcompat.app.AppCompatActivity
import com.example.iamwaiter.R
import com.example.iamwaiter.databinding.FragmentEnterBinding

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
        (activity as AppCompatActivity?)!!.getSupportActionBar()!!.hide()
        binding.button.setOnClickListener(){
            activity?.actionBar?.show()
            parentFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MainFragment()).commit()
        }
    }
}