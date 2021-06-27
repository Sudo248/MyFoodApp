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
import com.duonglh.myfoodapp.databinding.FragmentDetailBinding
import com.duonglh.myfoodapp.model.Product
import com.duonglh.myfoodapp.viewmodel.SuperManager


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    lateinit var initManager: ()-> SuperManager
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
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).binding.bottomNavigation.visibility = View.GONE

        manager.liveDataProductDetail.observe(viewLifecycleOwner, Observer {
            bindProductDetail(it)
            Log.d("Product Detail", it.toString())
        })

        binding.backButtonDetail.setOnClickListener{
            (activity as MainActivity).onBackPressed()
        }

    }

    private fun bindProductDetail(productDetail: Product){
        with(binding){
            imageProductDetail.setImageResource(productDetail.imageSrc)
            nameProductDetail.text = productDetail.name
            rating.rating = productDetail.stars
            starsDetail.text = productDetail.stars.toString()
            pricesProductDetail.text = "${productDetail.price}.000đ"
            val sold = productDetail.sold
            if(sold < 1000){
                soldDetail.text = "Đã bán: "+sold.toString()
            }else{
                soldDetail.text = "Đã bán: %.2fk".format((sold/100F))
            }
            buyProductDetail.setOnClickListener {
                manager.setListPayment(listOf(Pair(1,productDetail)))
                Navigation.findNavController(root).navigate(R.id.action_detailFragment_to_paymentFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).binding.bottomNavigation.visibility = View.VISIBLE
    }

}