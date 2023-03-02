package com.test.core_network.data.response

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class FlashSaleResponse(
    @SerializedName("flash_sale")
    @Expose
    val flashSale: List<FlashSaleResponseItem>? = null
)
