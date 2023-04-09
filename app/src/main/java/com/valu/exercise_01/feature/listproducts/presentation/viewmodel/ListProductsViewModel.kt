package com.valu.exercise_01.feature.listproducts.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valu.exercise_01.feature.listproducts.data.model.Product
import com.valu.exercise_01.feature.listproducts.domain.usecase.GetProductsUseCase
import com.valu.exercise_01.feature.listproducts.presentation.viewmodel.ProductsContractor.ListProductsViewState
import com.valu.exercise_01.feature.listproducts.presentation.viewmodel.ProductsContractor.ProductsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListProductsViewModel @Inject constructor(private val useCase: GetProductsUseCase) :
    ViewModel() {
    private val productsLiveData: MutableLiveData<ListProductsViewState> =
        MutableLiveData()
    private val products: MutableList<Product> = mutableListOf()

    fun getProducts() {
        viewModelScope.launch {
            val state = when (val result = useCase.execute()) {
                ProductsResult.GetProductsEmpty -> {

                    ListProductsViewState.GetProductsEmpty
                }
                ProductsResult.GetProductsFailed -> {
                    ListProductsViewState.GetProductsFailed
                }
                is ProductsResult.GetMessagesSuccess -> {
                    products.addAll(result.products)
                    ListProductsViewState.GetProductsSuccess(result.products)
                }
            }
            productsLiveData.postValue(state)
        }
    }

    fun getProductsLiveData() = productsLiveData
}