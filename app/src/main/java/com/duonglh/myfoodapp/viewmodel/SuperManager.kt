package com.duonglh.myfoodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.duonglh.myfoodapp.R
import com.duonglh.myfoodapp.model.*

class SuperManager : ViewModel() {

    private val _liveDataListProductType = MutableLiveData<List<ProductType>>()
    val listProductType: LiveData<List<ProductType>> = _liveDataListProductType
    fun setListProductType(data: List<ProductType>){
        _liveDataListProductType.value = data
    }

    private var _liveDataListUser = MutableLiveData<List<User>>()
    val liveDataListUser: LiveData<List<User>> = _liveDataListUser

    private var _liveDataListOrder = MutableLiveData<List<DataOrderProduct>>()
    val liveDataListOrder: LiveData<List<DataOrderProduct>> = _liveDataListOrder
    fun setListOrder(data: List<DataOrderProduct>){
        _liveDataListOrder.value = data
    }
    fun removeItemBought(){
        val data = _liveDataListOrder.value as MutableList
        data.removeAll { it.isChecked }
        _liveDataListOrder.value = data
    }

    fun setOrderProductChanged(data: DataOrderProduct, position: Int){
        _liveDataListOrder.value?.get(position)?.isChecked = data.isChecked
        _liveDataListOrder.value?.get(position)?.counts = data.counts
        _liveDataListPayment.value =
            _liveDataListOrder.value?.filter { it.isChecked }?.map{ Pair(it.counts,it.product) }
        Log.d("update count", data.counts.toString())
    }

    fun addOrderProduct(data: DataOrderProduct){
        val list = mutableListOf<DataOrderProduct>()
        _liveDataListOrder.value?.let { list.addAll(it) }
        list.add(data)
        _liveDataListOrder.value = list
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
    fun setProductDetailFavoriteChanged(isFavorite: Boolean){
        _liveDataProductDetail.value?.isFavorite = isFavorite
    }

    private val _livaDataVoucher = MutableLiveData<Voucher?>()
    val liveDataVoucher: LiveData<Voucher?> = _livaDataVoucher
    fun setVoucher(data: Voucher?){
        _livaDataVoucher.value = data
    }
    var listVoucher = mutableListOf<Voucher>()

    init{
        liveDataListOrder.observeForever(Observer { list ->
            _liveDataListPayment.value = list.filter{ it.isChecked }.map{ Pair(it.counts,it.product) }
        })

        val listProduct = mutableListOf(
            Product(1,"Zing Burger", R.drawable.zing_burger,25,4.5F,100,100, false),
            Product(2,"Salad", R.drawable.salad_and_tomatoes,10,4.0F,20,25, false),
            Product(3,"Octimus", R.drawable.octimus,50,5F,120,1000, false),
            Product(4,"Noodle", R.drawable.noodle,40,4.6F,100,123, false),
            Product(5,"Burger", R.drawable.burger,15,3.5F,20,50, false),
            Product(6,"OK", R.drawable.zing_burger,60,5F,100,100, false),
            Product(5,"Beef", R.drawable.ok1,100,4.5F,200,1200, true),
            Product(5,"Pizza", R.drawable.ok2,50,3.5F,200,500, false),
            Product(5,"Fizz", R.drawable.ok3,60,1.5F,200,50, false),
        )
        val list1 = listProduct.shuffled().toMutableList()
        val list2 = listProduct.shuffled().toMutableList()
        list2.shuffled()
        val listProductType = listOf(
            ProductType(1,"Fast Food", R.drawable.ic_burger,listProduct),
            ProductType(2,"Boiled", R.drawable.ic_boiled, list1),
            ProductType(3,"Pizza", R.drawable.ic_pizza_slice, list2),
        )

        listVoucher.addAll(listOf(
            Voucher(
                id = 1,
                name = "Khuyễn mãi ngày 6-6",
                percents = 25,
                isFreeShip = true,
                dueDate = 5
            ),
            Voucher(
                id = 2,
                name = "Khuyễn mãi ngày 7-7",
                percents = 0,
                isFreeShip = true,
                dueDate = 6
            ),
            Voucher(
                id = 3,
                name = "Khuyễn mãi ngày 8-8",
                percents = 25,
                isFreeShip = false,
                dueDate = 7
            ),
        ))

        _liveDataListProductType.value = listProductType
    }


}