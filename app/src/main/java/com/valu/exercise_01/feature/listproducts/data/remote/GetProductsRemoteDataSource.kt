package com.valu.exercise_01.feature.listproducts.data.remote

import com.valu.exercise_01.base.data.NetworkResponse
import com.valu.exercise_01.base.data.NetworkServiceCall
import com.valu.exercise_01.feature.listproducts.data.model.Product

interface GetProductsRemoteDataSource : NetworkServiceCall {
    suspend fun getProductsList(): NetworkResponse<List<Product>>
}