package com.valu.exercise_01.feature.listproducts.presentation.viewmodel

import com.valu.exercise_01.feature.listproducts.data.model.Product

interface ProductsContractor {

    sealed class ProductsResult {
        data class GetMessagesSuccess(val products: List<Product>) : ProductsResult()
        object GetProductsFailed : ProductsResult()
        object GetProductsEmpty : ProductsResult()
    }

    sealed class ListProductsViewState {
        data class GetProductsSuccess(val products: List<Product>) : ListProductsViewState()
        object GetProductsFailed : ListProductsViewState()
        object GetProductsEmpty : ListProductsViewState()
    }
}