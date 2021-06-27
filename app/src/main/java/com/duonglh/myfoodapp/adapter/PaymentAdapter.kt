package com.duonglh.myfoodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duonglh.myfoodapp.databinding.ItemPaymentBinding
import com.duonglh.myfoodapp.model.Product

class PaymentAdapter : RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemPaymentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: Pair<Int, Product>){
            val product = data.second
            with(binding){
                imageProductPayment.setImageResource(product.imageSrc)
                nameProductPayment.text = product.name
                pricesProductPayment.text = "${product.price}.000đ"
                countProductPayment.text = data.first.toString()
            }
        }
    }

    var data = listOf<Pair<Int, Product>>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPaymentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}