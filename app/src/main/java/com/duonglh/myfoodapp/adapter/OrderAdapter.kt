package com.duonglh.myfoodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.duonglh.myfoodapp.databinding.ItemOrderBinding
import com.duonglh.myfoodapp.model.DataOrderProduct

class OrderAdapter(val checkBox: (DataOrderProduct, Int)->Unit) :
    ListAdapter<DataOrderProduct, OrderAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<DataOrderProduct>(){
            override fun areItemsTheSame(
                oldItem: DataOrderProduct,
                newItem: DataOrderProduct
            ): Boolean {
                return oldItem.product.id == newItem.product.id && oldItem.isChecked == newItem.isChecked
            }

            override fun areContentsTheSame(
                oldItem: DataOrderProduct,
                newItem: DataOrderProduct
            ): Boolean {
                return oldItem.product == newItem.product
            }
        }
    ){
    inner class ViewHolder(private val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bin(data: DataOrderProduct, position: Int){
            val order = data.product
            with(binding) {
                imageOrder.setImageResource(order.imageSrc)
                nameOrder.text = order.name
                pricesOrder.text = "${order.price}.000đ"
                var count = data.counts
                countsOrder.text = count.toString()
                minusOrder.setOnClickListener {
                    if(count > 1) {
                        countsOrder.text = (--count).toString()
                    }
                }

                addOrder.setOnClickListener {
                    countsOrder.text = (++count).toString()
                }

                checkboxItem.isChecked = data.isChecked

                checkboxItem.setOnCheckedChangeListener { _, isChecked ->
                    data.isChecked = isChecked
                    data.counts = count
                    checkBox(data, position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemOrderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bin(getItem(position), position)
    }

}