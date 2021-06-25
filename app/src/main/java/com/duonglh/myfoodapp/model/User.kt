package com.duonglh.myfoodapp.model

data class User(
    var name: String,
    var password: String,
    var gender: String,
    var phoneNumber: String,
    var email: String,
    var address: String,
    var bank: String,
    val order: MutableList<Product>
)
