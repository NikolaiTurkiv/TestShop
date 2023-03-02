package com.test.feature_product

data class LatestItem(
    val category: String? = null,
    val name: String? = null,
    val price: Int? = null,
    val imageUrl: String? = null,
): ListItem