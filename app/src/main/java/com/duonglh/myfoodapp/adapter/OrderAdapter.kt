package com.duonglh.myfoodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.duonglh.myfoodapp.databinding.ItemOrderBinding
import com.duonglh.myfoodapp.model.Product

class OrderAdapter(val checkBox: (Boolean, Int)->Unit) :
    ListAdapter<Product, OrderAdapter.ViewHolder>(DifferanceProduct()){
    inner class ViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bin(order: Product, position: Int){
            binding.apply {
                imageOrder.setImageResource(order.imageSrc)
                nameOrder.text = order.name
                pricesOrder.text = "${order.price}.0000đ"

                minusOrder.setOnClickListener {
                    var count = countsOrder.text.toString().toInt()
                    if(count > 1) {
                        countsOrder.text = (--count).toString()
                    }
                }

                addOrder.setOnClickListener {
                    var count = countsOrder.text.toString().toInt()
                    countsOrder.text = (++count).toString()
                }

                checkboxItem.setOnCheckedChangeListener { _, isChecked ->
                    checkBox(isChecked, position)
                }
            }
        }
    }

    var data = listOf<Product>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemOrderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bin(data[position], position)
    }

    override fun getItemCount(): Int = data.size
}