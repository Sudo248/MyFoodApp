package com.duonglh.myfoodapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duonglh.myfoodapp.R
import com.duonglh.myfoodapp.model.Product
import com.duonglh.myfoodapp.model.ProductType
import com.duonglh.myfoodapp.model.User

class SuperManager : ViewModel() {

    private val _liveDataListProductType = MutableLiveData<List<ProductType>>()
    val listProductType: LiveData<List<ProductType>> = _liveDataListProductType
    fun setListProductType(data: List<ProductType>){
        _liveDataListProductType.value = data
    }

    private var _liveDataListUser = MutableLiveData<List<User>>()
    val liveDataListUser: LiveData<List<User>> = _liveDataListUser

    private var _liveDataListOrder = MutableLiveData<List<Product>>()
    val liveDataListOrder: LiveData<List<Product>> = _liveDataListOrder
    fun setListOrder(data: List<Product>){
        _liveDataListOrder.value = data
    }

    private val _liveDataListPayment = MutableLiveData<List<Pair<Int,Product>>>()
    val liveDataListPayment: LiveData<List<Pair<Int,Product>>> = _liveDataListPayment
    fun setListPayment(data: List<Pair<Int,Product>>){
        _liveDataListPayment.value = data
    }

    private val _liveDataProductDetail = MutableLiveData<Product>()
    val liveDataProductDetail: LiveData<Product> = _liveDataProductDetail
    fun setProductDetail(data: Product){
        _liveDataProductDetail.value = data
    }

    init{

        val listProduct = mutableListOf(
            Product(1,"Zing Burger", R.drawable.zing_burger,25,4.5F,100,100),
            Product(2,"Salad", R.drawable.salad_and_tomatoes,10,4.0F,20,25),
            Product(3,"Octimus", R.drawable.octimus,50,5F,120,1000),
            Product(4,"Noodle", R.drawable.noodle,40,4.6F,100,123),
            Product(5,"Burger", R.drawable.burger,15,3.5F,20,50),
            Product(6,"OK", R.drawable.zing_burger,60,5F,100,100)
        )

        val listProductType = listOf(
            ProductType(1,"Fast Food", R.drawable.ic_burger,listProduct),
            ProductType(2,"Boiled", R.drawable.ic_boiled, listProduct),
            ProductType(3,"Pizza", R.drawable.ic_pizza_slice, mutableListOf()),
        )

        _liveDataListProductType.value = listProductType
    }


}