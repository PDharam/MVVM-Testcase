package com.pdharam.mvvmtest.repository

import com.pdharam.mvvmtest.api.ProductAPI
import com.pdharam.mvvmtest.models.ProductListItem
import com.pdharam.mvvmtest.utils.NetworkResult
import com.pdharam.mvvmtest.utils.NetworkResult.Error
import com.pdharam.mvvmtest.utils.NetworkResult.Success

class ProductRepository(val productAPI: ProductAPI) {

    suspend fun getProducts(): NetworkResult<List<ProductListItem>> {
        val response = productAPI.getProducts()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                Success(responseBody)
            } else {
                Error("Something went wrong")
            }
        } else {
            Error("Something went wrong")
        }
    }
}