package com.pdharam.mvvmtest.repository

import com.pdharam.mvvmtest.api.ProductAPI
import com.pdharam.mvvmtest.models.ProductListItem
import com.pdharam.mvvmtest.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ProductRepositoryTest {
    @Mock
    lateinit var productAPI: ProductAPI

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testGetProduct_expectedEmptyList() = runTest {
        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.success(emptyList()))
        val repository = ProductRepository(productAPI)

        val result = repository.getProducts()

        assertEquals(true, result is NetworkResult.Success)
        assertEquals(0, result.data!!.size)
    }

    @Test
    fun testGetProduct_expectedProductList() = runTest {
        val productList = listOf(
            ProductListItem("", "", 1, "", 40.3, "Prod 1"),
            ProductListItem("", "", 2, "", 38.3, "Prod 2")
        )
        Mockito.`when`(productAPI.getProducts()).thenReturn(Response.success(productList))
        val repository = ProductRepository(productAPI)

        val result = repository.getProducts()

        assertEquals(true, result is NetworkResult.Success)
        assertEquals(2, result.data!!.size)
        assertEquals("Prod 1", result.data!![0].title)
    }

    @Test
    fun testGetProduct_expectedError() = runTest {
        val productList = listOf(
            ProductListItem("", "", 1, "", 40.3, "Prod 1"),
            ProductListItem("", "", 2, "", 38.3, "Prod 2")
        )
        Mockito.`when`(productAPI.getProducts())
            .thenReturn(Response.error(401, "Unauthorised".toResponseBody()))
        val repository = ProductRepository(productAPI)

        val result = repository.getProducts()

        assertEquals(true, result is NetworkResult.Error)
        assertEquals("Something went wrong", result.message)
    }
}