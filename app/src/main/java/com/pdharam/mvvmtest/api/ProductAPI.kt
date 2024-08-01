package com.pdharam.mvvmtest.api

import com.pdharam.mvvmtest.models.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    @GET("/products")
    suspend fun getProducts(): Response<List<ProductListItem>>
}