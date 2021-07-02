package com.duonglh.myfoodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.duonglh.myfoodapp.databinding.ItemVoucherBinding
import com.duonglh.myfoodapp.model.Voucher

class VoucherAdapter(list: List<Voucher>) : RecyclerView.Adapter<VoucherAdapter.ViewHolder>() {

    var lastCheckBox: CheckBox? = null

    val data: MutableList<Pair<Boolean,Voucher>> = list.map { Pair(false, it) }.toMutableList()

    fun voucherChecked(): Voucher?{
        for(i in data){
            if(i.first){
                return i.second
            }
        }
        return null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVoucherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(val binding: ItemVoucherBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Pair<Boolean, Voucher>, position: Int){
            val voucher = data.second
            with(binding){
                nameVoucher.text = voucher.name
                reducePricePercent.text = "Giảm giá: "+voucher.percents.toString()+"%"
                isFreeShip.text = "Free ship: "+voucher.isFreeShip.toString()
                dueDate.text = "HSD: "+voucher.dueDate.toString()

                checkBoxVoucher.setOnCheckedChangeListener { view, isChecked ->
                    if(isChecked){
                        lastCheckBox?.let{
                            lastCheckBox!!.isChecked = false
                        }
                        lastCheckBox = view as CheckBox
                    }
                    else {
                        lastCheckBox = null
                    }
                    this@VoucherAdapter.data[position] = Pair(isChecked,data.second)
                }
            }
        }
    }
}