package com.duonglh.myfoodapp.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.duonglh.myfoodapp.adapter.VoucherAdapter
import com.duonglh.myfoodapp.databinding.FragmentVoucherBinding
import com.duonglh.myfoodapp.viewmodel.SuperManager


class VoucherFragment : Fragment() {

    lateinit var binding: FragmentVoucherBinding
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
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVoucherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = VoucherAdapter(manager.listVoucher)

        with(binding){
            backButtonVoucher.setOnClickListener {
                requireActivity().onBackPressed()
            }
            rcvVoucher.adapter = adapter
            rcvVoucher.setHasFixedSize(true)
            agreeVoucherButton.setOnClickListener {
                if(adapter.sumVoucherChecked > 1){
                    val toast = Toast.makeText(requireContext(), "Bạn không thể chọn quá 2 voucher cho 1 lần đặt hàng!",Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER,0,0)
                    toast.show()
                }
                else{
                    manager.setVoucher(adapter.voucherChecked())
                    requireActivity().onBackPressed()
                }
            }
        }
    }


}