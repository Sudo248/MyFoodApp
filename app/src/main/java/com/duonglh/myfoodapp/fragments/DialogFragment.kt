package com.duonglh.myfoodapp.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.duonglh.myfoodapp.databinding.FragmentDialogBinding
import com.duonglh.myfoodapp.model.Product
import com.duonglh.myfoodapp.viewmodel.SuperManager


class DialogFragment(val toPaymentFragment: ()->Unit) : DialogFragment() {

    lateinit var binding: FragmentDialogBinding
    lateinit var initManager:()->SuperManager
    private val manager: SuperManager by lazy { initManager() }
    lateinit var data: Pair<Int, Product>
    private var count = 0

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
        dialog?.window?.setGravity(Gravity.BOTTOM)
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manager.liveDataListPayment.observe(viewLifecycleOwner, Observer {
            data = it.first()
            showProduct()
        })

        with(binding){
            closeDialogButton.setOnClickListener { dismiss() }
            buyDialogButton.setOnClickListener {
                manager.setListPayment(listOf(Pair(count, data.second)))
                dismiss()
                toPaymentFragment()
            }

        }
    }

    private fun showProduct(){
        with(binding){
            count = data.first
            countsOrder.text = count.toString()
            minusOrder.setOnClickListener {
                if(count > 1) {
                    countsOrder.text = (--count).toString()
                    sumMoneyDialog.text = "${data.second.price * count}.000đ"
                }
            }
            addOrder.setOnClickListener {
                countsOrder.text = (++count).toString()
                sumMoneyDialog.text = "${data.second.price * count}.000đ"
            }
            imageOrderDialog.setImageResource(data.second.imageSrc)
            nameOrderDialog.text = data.second.name
            pricesOrderDialog.text = data.second.price.toString()+".000đ"
            sumMoneyDialog.text = "${data.second.price}.000đ"
        }
    }


}