package com.test.feature_product

data class FlashSaleItem(
    val category: String? = null,
    val name: String? = null,
    val price: Double? = null,
    val discount: Int? = null,
    val imageUrl: String? = null,
): ListItem