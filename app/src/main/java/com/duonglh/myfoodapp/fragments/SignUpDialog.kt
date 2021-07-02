package com.duonglh.myfoodapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.duonglh.myfoodapp.databinding.FragmentSignUpDialogBinding
import com.duonglh.myfoodapp.model.User
import com.duonglh.myfoodapp.viewmodel.SuperManager

class SignUpDialog(val toDiscoveryFragment:()->Unit) : DialogFragment() {

    lateinit var email: String
    lateinit var pass: String
    lateinit var initManager:()->SuperManager
    val manager: SuperManager by lazy { initManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            email = it.getString("email","")
            pass = it.getString("pass","")
        }
        initManager = {
            ViewModelProvider(requireActivity()).get(SuperManager::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSignUpDialogBinding.inflate(inflater, container, false)
        binding.emailDialogSignUp.text = email
        binding.registerButtonSignUp.setOnClickListener {
            val userName = binding.nameUserSignUp
            val gender = binding.genderUserSignUp
            val phoneNumber = binding.phoneNumberSignUp
            val address = binding.addressSignUp
            val bank = binding.bankSignUp

            if(userName.text.toString() == ""){
                userName.isFocusable = true
                userName.requestFocus()
            }
            else if(gender.text.toString() == ""){
                gender.setSelection(0)
            }
            else if(phoneNumber.text.toString() == ""){
                phoneNumber.setSelection(0)
            }
            else if(address.text.toString() == ""){
                address.setSelection(0)
            }
            else if(bank.text.toString() == ""){
                bank.setSelection(0)
            }else{
                val user = User(
                    name = userName.text.toString(),
                    password = pass,
                    gender = gender.text.toString(),
                    phoneNumber = phoneNumber.text.toString(),
                    email = email,
                    address = address.text.toString(),
                    bank = bank.text.toString(),
                    order = null)
                manager.addNewUser(user)
                this.dismiss()
                toDiscoveryFragment()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}