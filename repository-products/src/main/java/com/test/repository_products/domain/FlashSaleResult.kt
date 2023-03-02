package com.test.repository_products.domain

data class FlashSaleResult(
    val category: String? = null,
    val name: String? = null,
    val price: Double? = null,
    val discount: Int? = null,
    val imageUrl: String? = null,
)
