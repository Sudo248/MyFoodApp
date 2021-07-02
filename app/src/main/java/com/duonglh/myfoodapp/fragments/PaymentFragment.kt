package com.duonglh.myfoodapp.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.duonglh.myfoodapp.MainActivity
import com.duonglh.myfoodapp.R
import com.duonglh.myfoodapp.adapter.PaymentAdapter
import com.duonglh.myfoodapp.databinding.FragmentPaymentBinding
import com.duonglh.myfoodapp.model.Voucher
import com.duonglh.myfoodapp.viewmodel.SuperManager
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class PaymentFragment : Fragment() {

    lateinit var binding: FragmentPaymentBinding
    lateinit var initManager:()->SuperManager
    private val manager: SuperManager by lazy { initManager() }
    private var sumMoneyProduct = 0
    private val priceShipper = Random.nextInt(10,100)
    private var sumProduct = 0

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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PaymentAdapter()

        manager.liveDataListPayment.observe(viewLifecycleOwner, Observer { list ->
            sumMoneyProduct = 0
            sumProduct = 0
            adapter.data = list
            list.forEach {
                sumMoneyProduct += (it.first * it.second.price)
                sumProduct += it.first
            }
            showSumMoneyPayment()
            manager.liveDataVoucher.value?.let{
                applyVoucher(it)
            }
            Log.d("sum money payment", sumMoneyProduct.toString())
        })

        with(binding){
            rcvPayment.adapter = adapter
//            rcvPayment.setHasFixedSize(true)

            buyButton.setOnClickListener {
                val t = Toast.makeText(requireContext(),"Cảm ơn bạn đã đã đặt hàng!", Toast.LENGTH_LONG)
                t.setGravity(Gravity.CENTER,0,0)
                t.show()
                manager.setListPayment(listOf())
                manager.setVoucher(null)
                manager.removeItemBought()
                findNavController().navigate(R.id.action_paymentFragment_to_discoveryFragment)
            }
            constrainVoucherPayment.setOnClickListener {
                findNavController().navigate(R.id.action_paymentFragment_to_voucherFragment)
            }
        }

    }

    fun showSumMoneyPayment() {
//        val currency = NumberFormat.getCurrencyInstance(Locale.US)
        with(binding){
            backButtonPayment.setOnClickListener { (activity as MainActivity).onBackPressed() }
            sumProductPayment.text = "${sumMoneyProduct}.000đ"//currency.format(sumMoneyProduct)
            this.priceShipper.text = "${this@PaymentFragment.priceShipper}.000đ"//currency.format(priceShipper)
            finalSumProductPayment.text = "${sumMoneyProduct+this@PaymentFragment.priceShipper}.000đ" //currency.format(sumMoneyProduct+priceShipper)
            finalSumProductPayment1.text = finalSumProductPayment.text
            textSumProductPayment.text = "Tổng tiền(${sumProduct} sản phẩm): "
            sumShipperPayment.text = this.priceShipper.text
        }
    }
    fun applyVoucher(voucher: Voucher?){
        voucher?.let{
            with(binding){
                if(voucher.isFreeShip){
                    this.priceShipper.text = "0đ"
                    sumShipperPayment.text = "0đ"
                    sumMoneyProduct -= (sumMoneyProduct * voucher.percents /100)
                    finalSumProductPayment.text = "$sumMoneyProduct.000đ"
                    finalSumProductPayment1.text = finalSumProductPayment.text
                }
                else{
                    var sum = sumMoneyProduct+this@PaymentFragment.priceShipper
                    sum -= (sum * voucher.percents /100)
                    finalSumProductPayment.text = "$sum.000đ"
                    finalSumProductPayment1.text = finalSumProductPayment.text
                }
                percentVoucherPayment.text = voucher.percents.toString()+"%"
            }
        }

    }


}