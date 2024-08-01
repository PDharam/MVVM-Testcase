package com.pdharam.mvvmtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdharam.mvvmtest.adapter.ProductAdapter
import com.pdharam.mvvmtest.utils.NetworkResult
import com.pdharam.mvvmtest.viewmodel.MainViewModel
import com.pdharam.mvvmtest.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ProductAdapter
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.productList)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val repository = (application as StoreApplication).productRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))
            .get(MainViewModel::class.java)

        mainViewModel.getProducts()

        mainViewModel.product.observe(this, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d(TAG, it.data.toString())
                    adapter = ProductAdapter(it.data!!)
                    recyclerView.adapter = adapter

                }

                is NetworkResult.Error -> {}
                is NetworkResult.Loading -> {}
            }
        })
    }
}