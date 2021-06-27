package com.duonglh.myfoodapp.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.duonglh.myfoodapp.MainActivity
import com.duonglh.myfoodapp.adapter.PaymentAdapter
import com.duonglh.myfoodapp.databinding.FragmentPaymentBinding
import com.duonglh.myfoodapp.viewmodel.SuperManager
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class PaymentFragment : Fragment() {

    lateinit var binding: FragmentPaymentBinding
    lateinit var initManager:()->SuperManager
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
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PaymentAdapter()
        var sumMoneyProduct = 0
        val priceShipper = Random.nextInt()%100+10

        manager.liveDataListPayment.observe(viewLifecycleOwner, Observer { list ->
            adapter.data = list
            list.map { sumMoneyProduct += it.first }
        })

        with(binding){
            backButtonPayment.setOnClickListener { (activity as MainActivity).onBackPressed() }
            rcvPayment.adapter = adapter
            sumProductPayment.text = "${sumMoneyProduct.toString()}.000đ"
            this.priceShipper.text = "${priceShipper.toString()}.000đ"
            finalSumProductPayment.text = "${(sumMoneyProduct+priceShipper).toString()}.000đ"
            finalSumProductPayment1.text = finalSumProductPayment.text
            textSumProductPayment.text = "Tổng tiền(${adapter.data.size} sản phẩm): "
            sumShipperPayment.text = this.priceShipper.text
            buyButton.setOnClickListener {
                val t = Toast.makeText(requireContext(),"Cảm ơn bạn đã đã đặt hàng!",Toast.LENGTH_LONG)
                t.setGravity(Gravity.CENTER,0,0)
                t.show()
                requireActivity().onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        manager.setListPayment(listOf())
    }

}