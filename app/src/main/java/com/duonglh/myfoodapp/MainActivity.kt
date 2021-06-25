package com.duonglh.myfoodapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.duonglh.myfoodapp.databinding.ActivityMainBinding
import com.duonglh.myfoodapp.model.Product
import com.duonglh.myfoodapp.model.ProductType
import com.duonglh.myfoodapp.viewmodel.SuperManager

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val listProduct = mutableListOf<Product>()
    val listProductType = mutableListOf<ProductType>()
    override fun onCreate(savedInstanceState: Bundle?) {
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
        val superManager = ViewModelProvider(this).get(SuperManager::class.java)
        superManager.liveDataListProductType.value = listProductType
    }
    init{
        listProduct.addAll(listOf(
            Product(1,"Zing Burger",R.drawable.zing_burger,25,4.5F,100,100),
            Product(2,"Salad",R.drawable.salad_and_tomatoes,10,4.0F,20,25),
            Product(3,"Octimus",R.drawable.octimus,50,5F,120,1000),
            Product(4,"Noodle",R.drawable.noodle,40,4.6F,100,123),
            Product(5,"Burger",R.drawable.burger,15,3.5F,20,50),
            Product(6,"OK",R.drawable.zing_burger,60,5F,100,100)
        ))

        listProductType.addAll(listOf(
            ProductType(1,"Fast Food",R.drawable.ic_burger,listProduct),
            ProductType(2,"Boiled",R.drawable.ic_boiled, mutableListOf()),
            ProductType(3,"Pizza",R.drawable.ic_pizza_slice, mutableListOf()),
        ))
    }

}