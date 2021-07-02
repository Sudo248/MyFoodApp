package com.duonglh.myfoodapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.duonglh.myfoodapp.R
import com.duonglh.myfoodapp.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_discoveryFragment)
        }
        return binding.root
    }

}