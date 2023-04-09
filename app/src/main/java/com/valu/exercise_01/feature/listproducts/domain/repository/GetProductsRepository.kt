package com.valu.exercise_01.feature.listproducts.domain.repository

import com.valu.exercise_01.base.data.NetworkResponse
import com.valu.exercise_01.feature.listproducts.data.model.Product

interface GetProductsRepository {
    suspend fun getProductsList(): NetworkResponse<List<Product>>
}