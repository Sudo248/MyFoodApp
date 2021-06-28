package com.duonglh.myfoodapp.model

data class Product(
    val id: Int,
    val name: String,
    val imageSrc: Int,
    var price: Int,
    var stars: Float,
    var reviews: Int,
    var sold: Int,
    var isFavorite: Boolean
) {
    companion object {
        var Count: Int = 0
    }
    lateinit var imageUrl: String
}