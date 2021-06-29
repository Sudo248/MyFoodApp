package com.duonglh.myfoodapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duonglh.myfoodapp.databinding.ItemProductTypeBinding
import com.duonglh.myfoodapp.model.ProductType

class ProductTypeAdapter(val context: Context, val onItemCLickListener:(ProductType) -> Unit) :
    RecyclerView.Adapter<ProductTypeAdapter.ViewHolder>(){

    var currentItem = 0

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
        holder.bind(data[position])
        if(position == currentItem){
            holder.setColorOnClickItem()
        }
        else{
            holder.setColorNotClickItem()
        }
    }


    inner class ViewHolder(private val binding: ItemProductTypeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(productType: ProductType){
            with(binding) {
                imageProductType.setImageResource(productType.imageSrc)
                nameProductType.text = productType.name
                root.setOnClickListener {
                    onItemCLickListener(productType)
                    currentItem = adapterPosition
                    notifyDataSetChanged()
//                    cardViewPT.outlineAmbientShadowColor = context.getColor(R.color.selector_color)
//                    cardViewPT.outlineSpotShadowColor = context.getColor(R.color.selector_color)
//                    imgProductType.setColorFilter(ContextCompat.getColor(context, R.color.selector_color), PorterDuff.Mode.SRC_IN)
//                    nameProductType.setTextColor(R.color.selector_color)
                }

            }
        }

        fun setColorOnClickItem(){
            with(binding){
                root.strokeWidth = 4
                root.strokeColor = Color.RED
                imageProductType.setColorFilter(Color.RED)
                nameProductType.setTextColor(Color.RED)
            }
        }
        fun setColorNotClickItem(){
            with(binding){
                root.strokeWidth = 1
                root.strokeColor = Color.GRAY
                imageProductType.setColorFilter(Color.GRAY)
                nameProductType.setTextColor(Color.GRAY)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}