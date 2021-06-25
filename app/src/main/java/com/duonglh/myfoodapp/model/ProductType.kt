package com.duonglh.myfoodapp.model

data class ProductType(
    val id: Int,
    val name: String,
    val imageSrc: Int,
    val listProduct: MutableList<Product>
)