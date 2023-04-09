package com.valu.exercise_01

import com.valu.exercise_01.base.data.NetworkResponse
import com.valu.exercise_01.feature.listproducts.data.model.Product
import com.valu.exercise_01.feature.listproducts.data.model.Rating
import com.valu.exercise_01.feature.listproducts.domain.repository.GetProductsRepository
import com.valu.exercise_01.feature.listproducts.domain.usecase.GetProductsUseCase
import com.valu.exercise_01.feature.listproducts.presentation.viewmodel.ProductsContractor
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class GetProductsUseCaseTest {

    private lateinit var useCase: GetProductsUseCase

    @Mock
    private lateinit var repository: GetProductsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetProductsUseCase(repository)
    }

    @Test
    fun getMessagesReturnEmptyResult() {
        val expected = ProductsContractor.ProductsResult.GetProductsEmpty

        val list: List<Product> = mutableListOf()
        val networkResponse = NetworkResponse.Success(list)

        runBlocking {

            Mockito.`when`(repository.getProductsList()).thenReturn(networkResponse)


            val actual = useCase.execute()

            Assert.assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun getMessagesReturnSuccessResult() {
        val inputList = listOf(Product(
            id = 1,
            title = "title",
            price = 1.2,
            description = "description",
            category = "category",
            image = "https//:image",
            rating = Rating(rate = 1.5, count = 32)
        ))
        val returnedList =
            listOf(Product(
                id = 1,
                title = "title",
                price = 1.2,
                description = "description",
                category = "category",
                image = "https//:image",
                rating = Rating(rate = 1.5, count = 32)
            ))
        val networkResponse = NetworkResponse.Success(inputList)

        val expected = ProductsContractor.ProductsResult.GetMessagesSuccess(returnedList)


        runBlocking {

            Mockito.`when`(repository.getProductsList()).thenReturn(networkResponse)


            val actual = useCase.execute()

            Assert.assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun getMessagesReturnFailedResult() {
        val networkResponse = NetworkResponse.Failure<List<Product>>(Exception())

        val expected = ProductsContractor.ProductsResult.GetProductsFailed


        runBlocking {

            Mockito.`when`(repository.getProductsList()).thenReturn(networkResponse)


            val actual = useCase.execute()

            Assert.assertEquals(
                expected,
                actual
            )
        }
    }
}