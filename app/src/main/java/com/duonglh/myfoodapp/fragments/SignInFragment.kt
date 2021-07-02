package com.duonglh.myfoodapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.duonglh.myfoodapp.R
import com.duonglh.myfoodapp.databinding.FragmentSignInBinding
import com.duonglh.myfoodapp.viewmodel.SuperManager

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSignInBinding.inflate(inflater, container, false)
        binding.signInButton.setOnClickListener {
            val manager = ViewModelProvider(requireActivity()).get(SuperManager::class.java)
            val emailOrNumber = binding.textFieldUserSignIn.editText?.text.toString()
            val pass = binding.textFieldPasswordSignIn.editText?.text.toString()
            Log.d("UserName", emailOrNumber)
            Log.d("Password", pass)
            binding.notificationSignIn.text = when(manager.checkUser(emailOrNumber,pass)){
                0 -> "Email or UserName Invalid"
                1 -> "Wrong Password"
                else -> {
                    findNavController().navigate(R.id.action_logInFragment_to_discoveryFragment)
                    ""
                }
            }
        }
        return binding.root
    }



}