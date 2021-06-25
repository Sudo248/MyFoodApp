package com.duonglh.myfoodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.duonglh.myfoodapp.model.Product
import com.duonglh.myfoodapp.model.ProductType
import com.duonglh.myfoodapp.model.User

class SuperManager : ViewModel() {
    val liveDataListProductType = MutableLiveData<List<ProductType>>()
    val liveDataListUser = MutableLiveData<List<User>>()
    val liveDataListOrder = MutableLiveData<List<Product>>()
}