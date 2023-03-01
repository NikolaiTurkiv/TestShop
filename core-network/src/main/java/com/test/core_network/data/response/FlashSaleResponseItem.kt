package com.test.core_network.data.response

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class FlashSaleResponseItem(
    @SerializedName("category")
    @Expose
    val category: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("price")
    @Expose
    val price: Double? = null,

    @SerializedName("discount")
    @Expose
    val discount: Int? = null,

    @SerializedName("image_url")
    @Expose
    val imageUrl: String? = null,
)

