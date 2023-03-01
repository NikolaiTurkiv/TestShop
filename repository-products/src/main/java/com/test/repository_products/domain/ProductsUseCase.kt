package com.test.repository_products.domain


class ProductsUseCase (
    private val repository: ProductsRepository
) {
    val listLatest = repository.getLatestProduct()
    val listFlashSale = repository.getFlashSale()
}