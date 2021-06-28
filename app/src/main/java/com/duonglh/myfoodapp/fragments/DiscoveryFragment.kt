package com.duonglh.myfoodapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.duonglh.myfoodapp.MainActivity
import com.duonglh.myfoodapp.R
import com.duonglh.myfoodapp.adapter.ProductAdapter
import com.duonglh.myfoodapp.adapter.ProductTypeAdapter
import com.duonglh.myfoodapp.databinding.FragmentDiscoveryBinding
import com.duonglh.myfoodapp.viewmodel.SuperManager

class DiscoveryFragment : Fragment() {

    lateinit var binding: FragmentDiscoveryBinding
    lateinit var productTypeAdapter: ProductTypeAdapter
    lateinit var productAdapter: ProductAdapter
    lateinit var initManager: ()->SuperManager
    private val manager: SuperManager by lazy { initManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initManager = {
            ViewModelProvider(requireActivity()).get(SuperManager::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentDiscoveryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(activity as MainActivity){
            binding.bottomNavigation.visibility = View.VISIBLE
            productTypeAdapter = ProductTypeAdapter(this){
//                TODO("click Item ProductType to show list Product")
                productAdapter.submitList(it.listProduct)
                this@DiscoveryFragment.binding.nameProductTypeDiscovery.text = it.name
            }
        }

        productAdapter = ProductAdapter {
//                TODO("click Item Product to open Detail Fragment")
            manager.setProductDetail(it)
            Log.d("Product", it.toString())
            Navigation.findNavController(view)
                .navigate(R.id.action_discoveryFragment_to_detailFragment)
        }

        manager.listProductType.observe(viewLifecycleOwner, Observer {
            productTypeAdapter.submitList(it)
        })

        with(binding){
            rcvCategoryDiscovery.adapter = productTypeAdapter
            rcvProductDiscovery.adapter = productAdapter
            imageUser.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_discoveryFragment_to_accountFragment)
            }
        }
    }

}