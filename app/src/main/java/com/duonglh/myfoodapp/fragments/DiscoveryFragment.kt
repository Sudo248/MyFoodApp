package com.duonglh.myfoodapp.fragments

import android.os.Bundle
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DiscoveryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiscoveryFragment : Fragment() {
    // TODO: Rename and change types of parameters

    lateinit var binding: FragmentDiscoveryBinding
    lateinit var superManager: SuperManager
    lateinit var productTypeAdapter: ProductTypeAdapter
    lateinit var productAdapter: ProductAdapter
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
        superManager = activity?.let { ViewModelProvider(it).get(SuperManager::class.java) }!!
        return inflater.inflate(R.layout.fragment_discovery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiscoveryBinding.bind(view)
        with(activity as MainActivity){
            binding.bottomNavigation.visibility = View.VISIBLE
            productTypeAdapter = ProductTypeAdapter(this){
//                TODO("click Item ProductType to show list Product")
                productAdapter.submitList(it.listProduct)
            }

            productAdapter = ProductAdapter {
//                TODO("click Item Product to open Item Fragment")
                Navigation.findNavController(this@DiscoveryFragment.binding.root)
                    .navigate(R.id.action_discoveryFragment_to_detailFragment)
            }
            superManager.liveDataListProductType.observe(this, Observer {
                productTypeAdapter.submitList(it)
            })
        }

        with(binding){
            rcvCategoryDiscovery.adapter = productTypeAdapter
            rcvProductDiscovery.adapter = productAdapter
            imageUser.setOnClickListener {
                Navigation.findNavController(root)
                    .navigate(R.id.action_discoveryFragment_to_accountFragment)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DiscoveryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiscoveryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}