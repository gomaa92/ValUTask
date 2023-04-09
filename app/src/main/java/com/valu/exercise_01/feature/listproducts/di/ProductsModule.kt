package com.valu.exercise_01.feature.listproducts.di

import com.valu.exercise_01.feature.listproducts.data.remote.GetProductsRemoteDataSource
import com.valu.exercise_01.feature.listproducts.data.remote.GetProductsRemoteDataSourceImpl
import com.valu.exercise_01.feature.listproducts.data.repository.GetProductsRepositoryImpl
import com.valu.exercise_01.feature.listproducts.domain.repository.GetProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class ProductsModule {

    @Binds
    abstract fun bindProductsRepository(repository: GetProductsRepositoryImpl): GetProductsRepository

    @Binds
    abstract fun bindProductsRemoteDataSourceModel(
        remoteDataSource: GetProductsRemoteDataSourceImpl
    ): GetProductsRemoteDataSource
}