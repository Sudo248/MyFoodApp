package com.duonglh.myfoodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.duonglh.myfoodapp.databinding.ItemProductTypeBinding
import com.duonglh.myfoodapp.model.ProductType

class ProductTypeAdapter(val context: Context, val onItemCLickListener:(ProductType) -> Unit) : ListAdapter<ProductType, ProductTypeAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<ProductType>(){
        override fun areItemsTheSame(oldItem: ProductType, newItem: ProductType): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: ProductType, newItem: ProductType): Boolean {
            return oldItem == newItem
        }
    }
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductTypeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<ProductType>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    inner class ViewHolder(private val binding: ItemProductTypeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(productType: ProductType){
            with(binding) {
                imageProductType.setImageResource(productType.imageSrc)
                nameProductType.text = productType.name
                root.setOnClickListener {
                    onItemCLickListener(productType)
//                    cardViewPT.outlineAmbientShadowColor = context.getColor(R.color.selector_color)
//                    cardViewPT.outlineSpotShadowColor = context.getColor(R.color.selector_color)
//                    imgProductType.setColorFilter(ContextCompat.getColor(context, R.color.selector_color), PorterDuff.Mode.SRC_IN)
//                    nameProductType.setTextColor(R.color.selector_color)
                }
                cardViewPT.setOnCheckedChangeListener { card, isChecked ->
                    card.isChecked = !isChecked
                }
            }
        }
    }
}