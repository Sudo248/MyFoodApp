package com.duonglh.myfoodapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.duonglh.myfoodapp.MainActivity
import com.duonglh.myfoodapp.R
import com.duonglh.myfoodapp.databinding.FragmentLogInBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LogInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LogInFragment : Fragment() {

    companion object{
        lateinit var view: View
    }

    lateinit var binding: FragmentLogInBinding
    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity
        LogInFragment.view = binding.root
        with(binding){
            signInButtonTitle.isPressed = true
            signUpButtonTitle.setOnClickListener {
                signUpButtonTitle.isPressed = true
                signInButtonTitle.isPressed = false
                Navigation.findNavController(binding.signFragment).navigate(R.id.action_signInFragment_to_signUpFragment)
            }
            
            signInButtonTitle.setOnClickListener {
                signInButtonTitle.isPressed = true
                signUpButtonTitle.isPressed = false
                Navigation.findNavController(binding.signFragment).navigate(R.id.action_signUpFragment_to_signInFragment)
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
    }

}