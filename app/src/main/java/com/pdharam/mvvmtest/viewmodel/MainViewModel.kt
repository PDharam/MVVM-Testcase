package com.pdharam.mvvmtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdharam.mvvmtest.models.ProductListItem
import com.pdharam.mvvmtest.repository.ProductRepository
import com.pdharam.mvvmtest.utils.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _products = MutableLiveData<NetworkResult<List<ProductListItem>>>()
    val product: LiveData<NetworkResult<List<ProductListItem>>>
        get() = _products

    fun getProducts() {
        viewModelScope.launch {
            val result = repository.getProducts()
            _products.postValue(result)
        }
    }
}