package com.duonglh.myfoodapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.duonglh.myfoodapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            greetingImg.setOnClickListener {
                it.visibility = View.GONE
                frame.visibility = View.VISIBLE
            }
            bottomNavigation.setupWithNavController(findNavController(R.id.fragment))
        }

    }


}