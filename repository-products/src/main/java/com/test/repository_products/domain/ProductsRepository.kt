package com.test.repository_products.domain

import io.reactivex.rxjava3.core.Single

interface ProductsRepository {
    fun getLatestProduct(): Single<List<LatestResult>>
    fun getFlashSale(): Single<List<FlashSaleResult>>
}
