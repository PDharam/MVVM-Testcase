package com.pdharam.mvvmtest

import android.app.Application
import com.pdharam.mvvmtest.api.ProductAPI
import com.pdharam.mvvmtest.repository.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreApplication : Application() {
    lateinit var productsAPI: ProductAPI
    lateinit var productRepository: ProductRepository
    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fakestoreapi.com/")
            .build()

        productsAPI = retrofit.create(ProductAPI::class.java)
        productRepository = ProductRepository(productsAPI)
    }
}