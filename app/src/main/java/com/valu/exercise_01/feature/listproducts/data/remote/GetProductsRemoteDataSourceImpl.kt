package com.valu.exercise_01.feature.listproducts.data.remote

import com.valu.exercise_01.base.data.NetworkResponse
import com.valu.exercise_01.feature.listproducts.data.model.Product
import javax.inject.Inject

class GetProductsRemoteDataSourceImpl @Inject constructor(private val service: ProductsService):GetProductsRemoteDataSource {
    override suspend fun getProductsList(): NetworkResponse<List<Product>> {
        return safeApiCall { service.getProducts() }
    }
}