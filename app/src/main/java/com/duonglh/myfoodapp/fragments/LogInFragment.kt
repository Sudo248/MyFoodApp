package com.duonglh.myfoodapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.duonglh.myfoodapp.adapter.PagerLogInAdapter
import com.duonglh.myfoodapp.databinding.FragmentLogInBinding
import com.google.android.material.tabs.TabLayoutMediator

class LogInFragment : Fragment() {

    lateinit var binding: FragmentLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        binding.pager.adapter = PagerLogInAdapter(this)
        TabLayoutMediator(binding.tabLogIn, binding.pager){ tab, position ->
            tab.text = when(position){
                0 -> "Sign In"
                else -> "Sign Up"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}