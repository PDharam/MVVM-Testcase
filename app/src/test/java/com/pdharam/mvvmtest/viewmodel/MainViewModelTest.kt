package com.pdharam.mvvmtest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pdharam.mvvmtest.getOrAwaitValue
import com.pdharam.mvvmtest.repository.ProductRepository
import com.pdharam.mvvmtest.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {


    @Mock
    lateinit var repository: ProductRepository

    @get:Rule
    val instantTaskExecuteRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun test_getProducts() = runTest {
        Mockito.`when`(repository.getProducts()).thenReturn(NetworkResult.Success(emptyList()))
        val sut = MainViewModel(repository)
        sut.getProducts()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.product.getOrAwaitValue()
        assertEquals(0, result.data!!.size)
    }

    @Test
    fun test_getProducts_expectedError() = runTest {
        Mockito.`when`(repository.getProducts())
            .thenReturn(NetworkResult.Error("Something went wrong"))
        val sut = MainViewModel(repository)
        sut.getProducts()
        testDispatcher.scheduler.advanceUntilIdle()
        val result = sut.product.getOrAwaitValue()
        assertEquals(true, result is NetworkResult.Error)
        assertEquals("Something went wrong", result.message)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}