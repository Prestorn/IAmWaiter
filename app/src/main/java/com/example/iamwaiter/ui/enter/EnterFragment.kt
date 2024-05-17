package com.example.iamwaiter.ui.enter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.example.iamwaiter.databinding.FragmentEnterBinding
import com.example.iamwaiter.R

class EnterFragment : Fragment() {

    lateinit var binding: FragmentEnterBinding
    lateinit var viewModel: EnterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val provider = ViewModelProvider(activity as ViewModelStoreOwner)
        viewModel = provider[EnterViewModel::class]

        // TODO delete
        binding.login.setText("test")
        binding.password.setText("test")
        // TODO delete

        binding.button.setOnClickListener(){
            if (viewModel.checkUserLogin(binding.login.text.toString(), binding.password.text.toString())) {
                findNavController().navigate(R.id.action_enterFragment_to_orderListFragment)
            }
            else{
                Toast.makeText(context,"Неправильный логин или пароль",Toast.LENGTH_LONG).show()
                binding.password.setText("")
            }
        }
    }
}