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
import com.duonglh.myfoodapp.R
import com.duonglh.myfoodapp.adapter.OrderAdapter
import com.duonglh.myfoodapp.databinding.FragmentOrderBinding
import com.duonglh.myfoodapp.model.DataOrderProduct
import com.duonglh.myfoodapp.viewmodel.SuperManager

class OrderFragment : Fragment() {
    lateinit var binding: FragmentOrderBinding
    lateinit var initManager:()->SuperManager
    private val manager: SuperManager by lazy { initManager() }
    lateinit var adapter: OrderAdapter
    var listOrderProDuct =  mutableListOf<DataOrderProduct>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initManager = {
            ViewModelProvider(requireActivity()).get(SuperManager::class.java)
        }

        adapter = OrderAdapter{ data, position ->
            Log.d("From Item Changed", data.isChecked.toString())
            manager.setOrderProductChanged(data, position)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //

        manager.liveDataListOrder.observe(viewLifecycleOwner, Observer { list ->
            Log.d("From observer adapter", list.toString())
            adapter.submitList(list)
           // manager.setListPayment(list.filter { it.isChecked }.map{ Pair(it.counts,it.product) })
            listOrderProDuct = list as MutableList<DataOrderProduct>
        })

        with(binding){
            checkAll.setOnCheckedChangeListener { _, isChecked ->
                Log.d("check All", isChecked.toString())
                manager.setListOrder(listOrderProDuct.map { DataOrderProduct(isChecked,it.counts,it.product) })
            }

            buyProductOrder.setOnClickListener {

                Navigation.findNavController(root).navigate(R.id.action_orderFragment_to_paymentFragment)
            }
            rcvOrder.adapter = adapter
//            rcvOrder.setHasFixedSize(true)

            constrainVoucherOrder.setOnClickListener {
                Navigation.findNavController(root).navigate(R.id.action_orderFragment_to_voucherFragment)
            }
        }
    }


}