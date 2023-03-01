package com.test.repository_products

import com.test.network.data.NetworkApi
import com.test.repository_products.data.ProductRepositoryImpl
import com.test.repository_products.domain.ProductsRepository
import com.test.repository_products.domain.ProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductRepositoryModule {

    @Provides
    @Singleton
    fun provideProducts(api: NetworkApi): ProductsRepository = ProductRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideProductsUseCase(productsRepository: ProductsRepository) =
        ProductsUseCase(productsRepository)
}