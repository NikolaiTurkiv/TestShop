package com.test.repository_products.utils

import com.test.core_network.data.response.FlashSaleResponseItem
import com.test.network.data.response.LatestResponseItem
import com.test.repository_products.domain.FlashSaleResult
import com.test.repository_products.domain.LatestResult

fun LatestResponseItem.toLatestResult(): LatestResult {
    return LatestResult(
        category = this.category,
        name = this.name,
        price = this.price,
        imageUrl = this.imageUrl,
    )
}

fun FlashSaleResponseItem.toFlashSaleResult(): FlashSaleResult {
    return FlashSaleResult(
        category = this.category,
        name = this.name,
        price = this.price,
        discount = this.discount,
        imageUrl = this.imageUrl,
    )
}