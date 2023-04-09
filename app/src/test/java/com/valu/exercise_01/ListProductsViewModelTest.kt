package com.valu.exercise_01

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.valu.exercise_01.feature.listproducts.domain.usecase.GetProductsUseCase
import com.valu.exercise_01.feature.listproducts.presentation.viewmodel.ListProductsViewModel
import com.valu.exercise_01.feature.listproducts.presentation.viewmodel.ProductsContractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ListProductsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: ListProductsViewModel

    @Mock
    lateinit var useCase: GetProductsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = ListProductsViewModel(
            useCase
        )
    }

    @Test
    fun getMessagesReturnEmptyState() = runBlocking {
        val expected = ProductsContractor.ListProductsViewState.GetProductsEmpty

        val result = ProductsContractor.ProductsResult.GetProductsEmpty

        runBlocking {

            Mockito.`when`(useCase.execute()).thenReturn(result)

            viewModel.getProducts()
            val actual = viewModel.getProductsLiveData().getOrAwaitValueTest()

            Assert.assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun getMessagesReturnFailedState() = runBlocking {
        val expected = ProductsContractor.ListProductsViewState.GetProductsFailed

        val result = ProductsContractor.ProductsResult.GetProductsFailed

        runBlocking {

            Mockito.`when`(useCase.execute()).thenReturn(result)

            viewModel.getProducts()
            val actual = viewModel.getProductsLiveData().getOrAwaitValueTest()

            Assert.assertEquals(
                expected,
                actual
            )
        }
    }

    @Test
    fun getMessagesReturnSuccessState() = runBlocking {
        val expected = ProductsContractor.ListProductsViewState.GetProductsSuccess(listOf())

        val result = ProductsContractor.ProductsResult.GetMessagesSuccess(listOf())

        runBlocking {

            Mockito.`when`(useCase.execute()).thenReturn(result)

            viewModel.getProducts()
            val actual = viewModel.getProductsLiveData().getOrAwaitValueTest()

            Assert.assertEquals(
                expected,
                actual
            )
        }
    }
}