package com.valu.exercise_01.feature.listproducts.data.remote

import com.valu.exercise_01.base.data.CloudConfig
import com.valu.exercise_01.feature.listproducts.data.model.Product
import retrofit2.http.GET

interface ProductsService {
    @GET(CloudConfig.GET_PRODUCTS_LIST)
    suspend fun getProducts(): List<Product>
}