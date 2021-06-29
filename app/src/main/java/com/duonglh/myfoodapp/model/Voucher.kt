package com.duonglh.myfoodapp.model

data class Voucher(
    val id: Int,
    val name: String,
    val percents: Int,
    val isFreeShip: Boolean,
    var dueDate: Int
)