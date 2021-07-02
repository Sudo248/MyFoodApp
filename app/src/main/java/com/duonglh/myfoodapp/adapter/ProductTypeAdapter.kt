package com.duonglh.myfoodapp.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duonglh.myfoodapp.databinding.ItemProductTypeBinding
import com.duonglh.myfoodapp.model.ProductType

class ProductTypeAdapter(val onItemCLickListener:(ProductType) -> Unit) :
    RecyclerView.Adapter<ProductTypeAdapter.ViewHolder>(){

    lateinit var lastItemSelected: ItemProductTypeBinding

    var data = listOf<ProductType>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductTypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
        if(position == 0){
            lastItemSelected = holder.binding
            holder.setColorOnClickItem(lastItemSelected)
            lastItemSelected.root.isSelected = true
        }
    }
    
    inner class ViewHolder(val binding: ItemProductTypeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(productType: ProductType, position: Int){
            with(binding) {
                imageProductType.setImageResource(productType.imageSrc)
                nameProductType.text = productType.name
                root.setOnClickListener {
                    Log.d("P Selected ", root.isSelected.toString())
                    if(!root.isSelected){
                        onItemCLickListener(productType)
                        setColorNotClickItem(lastItemSelected)
                        lastItemSelected.root.isSelected = false

                        setColorOnClickItem(this)
                        lastItemSelected = this
                        root.isSelected = true
                    }
                }

            }
        }

        fun setColorOnClickItem(item: ItemProductTypeBinding){
            with(item){
                root.strokeWidth = 4
                root.strokeColor = Color.RED
                imageProductType.setColorFilter(Color.RED)
                nameProductType.setTextColor(Color.RED)
            }
        }
        fun setColorNotClickItem(item: ItemProductTypeBinding){
            with(item){
                root.strokeWidth = 1
                root.strokeColor = Color.GRAY
                imageProductType.setColorFilter(Color.GRAY)
                nameProductType.setTextColor(Color.GRAY)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}