package com.duonglh.myfoodapp.model

data class User(
    var name: String,
    var password: String,
    var gender: String,
    var phoneNumber: String,
    var email: String,
    var address: String,
    var bank: String,
    val order: MutableList<Product>?
)

/*
* name        = Duonglh
* pass        = 123456
* gender      = nam
* phonenumber = 0123456789
* email       = duong@gmail.com
* address     = Ao Sen
* bank        = Techcombank
* order       = null
* */
