package com.valu.exercise_01.feature.listproducts.data.repository

import com.valu.exercise_01.base.data.NetworkResponse
import com.valu.exercise_01.feature.listproducts.data.model.Product
import com.valu.exercise_01.feature.listproducts.data.remote.GetProductsRemoteDataSource
import com.valu.exercise_01.feature.listproducts.domain.repository.GetProductsRepository
import javax.inject.Inject

class GetProductsRepositoryImpl @Inject constructor(private val remoteDataSource: GetProductsRemoteDataSource) :
    GetProductsRepository {
    override suspend fun getProductsList(): NetworkResponse<List<Product>> {
        return remoteDataSource.getProductsList()
    }
}