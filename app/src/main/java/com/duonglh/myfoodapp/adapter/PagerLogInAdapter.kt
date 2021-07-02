package com.duonglh.myfoodapp.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.duonglh.myfoodapp.fragments.SignInFragment
import com.duonglh.myfoodapp.fragments.SignUpFragment

class PagerLogInAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> SignInFragment()
        else -> SignUpFragment()
    }
}