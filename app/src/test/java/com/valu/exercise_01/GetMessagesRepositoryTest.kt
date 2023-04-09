package com.valu.exercise_01

import com.valu.exercise_01.base.data.NetworkResponse
import com.valu.exercise_01.feature.listproducts.data.model.Product
import com.valu.exercise_01.feature.listproducts.data.model.Rating
import com.valu.exercise_01.feature.listproducts.data.remote.GetProductsRemoteDataSource
import com.valu.exercise_01.feature.listproducts.data.repository.GetProductsRepositoryImpl
import com.valu.exercise_01.feature.listproducts.domain.repository.GetProductsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetMessagesRepositoryTest {

    private lateinit var repositroy: GetProductsRepository

    @Mock
    lateinit var remoteDataSource: GetProductsRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repositroy = GetProductsRepositoryImpl(remoteDataSource)
    }

    @Test
    fun getMessagesListReturnSuccess() {
        runBlocking {
            val product = Product(
                id = 1,
                title = "title",
                price = 1.2,
                description = "description",
                category = "category",
                image = "https//:image",
                rating = Rating(rate = 1.2, count = 100)
            )
            val list = mutableListOf<Product>()
            list.add(product)

            val networkResponse = NetworkResponse.Success<List<Product>>(list)

            Mockito.`when`(remoteDataSource.getProductsList()).thenReturn(networkResponse)

            Assert.assertEquals(networkResponse, repositroy.getProductsList())
        }
    }

    @Test
    fun getMessagesListReturnFailed() {
        runBlocking {

            val networkResponse = NetworkResponse.Failure<List<Product>>(Exception())

            Mockito.`when`(remoteDataSource.getProductsList()).thenReturn(networkResponse)

            Assert.assertEquals(networkResponse, repositroy.getProductsList())
        }
    }

}