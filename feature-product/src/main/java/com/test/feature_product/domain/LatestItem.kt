package com.test.feature_product.domain

data class LatestItem(
    val category: String? = null,
    val name: String? = null,
    val price: String? = null,
    val imageUrl: String? = null,
): ListItem