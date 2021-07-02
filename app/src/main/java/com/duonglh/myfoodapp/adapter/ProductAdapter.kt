package com.duonglh.myfoodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.duonglh.myfoodapp.databinding.ItemProductBinding
import com.duonglh.myfoodapp.model.Product
import kotlin.random.Random

class ProductAdapter(val onItemCLickListener: (Product)->Unit) :
    ListAdapter<Product, ProductAdapter.ViewHolder>(DifferanceProduct){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.apply{
                imageProduct.setImageResource(product.imageSrc)
                distances.text = "%.2f".format(Random.nextFloat())
                starts.text = product.stars.toString()
                nameProduct.text = product.name
                pricesProduct.text = "${product.price}.0000đ"
                root.setOnClickListener {
                    onItemCLickListener(product)
                }
            }
        }
    }
}

object DifferanceProduct : DiffUtil.ItemCallback<Product>(){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}