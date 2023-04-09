package com.valu.exercise_01.feature.listproducts.domain.usecase

import com.valu.exercise_01.base.data.NetworkResponse
import com.valu.exercise_01.base.domain.usecase.SuspendableUseCase
import com.valu.exercise_01.feature.listproducts.domain.repository.GetProductsRepository
import com.valu.exercise_01.feature.listproducts.presentation.viewmodel.ProductsContractor
import javax.inject.Inject

open class GetProductsUseCase @Inject constructor(private val repository: GetProductsRepository) :
    SuspendableUseCase<Unit, ProductsContractor.ProductsResult> {
    override suspend fun execute(input: Unit?): ProductsContractor.ProductsResult {

        return when (val result = repository.getProductsList()) {
            is NetworkResponse.Failure -> ProductsContractor.ProductsResult.GetProductsFailed
            is NetworkResponse.Success -> {
                if (result.data.isEmpty()) ProductsContractor.ProductsResult.GetProductsEmpty
                else {
                    ProductsContractor.ProductsResult.GetMessagesSuccess(result.data)
                }
            }
        }
    }
}