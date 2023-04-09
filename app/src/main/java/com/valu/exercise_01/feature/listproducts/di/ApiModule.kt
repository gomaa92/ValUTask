package com.valu.exercise_01.feature.listproducts.di

import com.valu.exercise_01.feature.listproducts.data.remote.ProductsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class ApiModule {
    @Provides
    fun provideProductsApi(retrofit: Retrofit): ProductsService {
        return retrofit.create(ProductsService::class.java)
    }
}