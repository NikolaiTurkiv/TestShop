package com.test.repository_products.data

import com.test.network.data.NetworkApi
import com.test.repository_products.domain.FlashSaleResult
import com.test.repository_products.domain.LatestResult
import com.test.repository_products.domain.ProductsRepository
import com.test.repository_products.utils.toFlashSaleResult
import com.test.repository_products.utils.toLatestResult
import io.reactivex.rxjava3.core.Single

class ProductRepositoryImpl(
    private val api: NetworkApi
) : ProductsRepository {

    override fun getLatestProduct(): Single<List<LatestResult>> {
        return api.getLatestProduct().map { response ->
            response.latest?.map { it.toLatestResult() } ?: listOf()
        }
    }

    override fun getFlashSale(): Single<List<FlashSaleResult>> {
        return api.getFlashSale().map { response ->
            response.flashSale?.map { it.toFlashSaleResult() } ?: listOf()
        }
    }
}
