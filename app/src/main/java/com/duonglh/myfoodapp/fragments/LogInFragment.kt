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
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity
        binding = FragmentLogInBinding.bind(view)
        LogInFragment.view = binding.root
        with(binding){
            signInButtonTitle.isPressed = true
            signUpButtonTitle.setOnClickListener {
                Navigation.findNavController(binding.signFragment).navigate(R.id.action_signInFragment_to_signUpFragment)
                signUpButtonTitle.isPressed = true
                signInButtonTitle.isPressed = false
            }
            
            signInButtonTitle.setOnClickListener {
                Navigation.findNavController(binding.signFragment).navigate(R.id.action_signUpFragment_to_signInFragment)
                signInButtonTitle.isPressed = true
                signUpButtonTitle.isPressed = false
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
    }

}