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
            val pass = binding.textFieldPasswordSignUp.editText?.text.toString()
            val conFirmPass = binding.textFieldConfirmPasswordSignUp.editText?.text.toString()
            if(pass == conFirmPass){
                binding.notificationSignUp.visibility = View.INVISIBLE
                val bundle = Bundle()
                bundle.putString("email", binding.textFieldUserSignUp.editText?.text.toString())
                bundle.putString("password", pass)
                val dialogSignUp = SignUpDialog {
                    findNavController().navigate(R.id.action_logInFragment_to_discoveryFragment)
                }
                dialogSignUp.arguments = bundle
                dialogSignUp.show(requireActivity().supportFragmentManager,"Dialog Sign Up")
            }
            else{
                binding.notificationSignUp.visibility = View.VISIBLE
            }

        }
        return binding.root
    }

}