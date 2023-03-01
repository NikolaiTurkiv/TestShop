package com.test.network.data.response

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class LatestResponseItem(
    @SerializedName("category")
    @Expose
    val category: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("price")
    @Expose
    val price: Int? = null,

    @SerializedName("image_url")
    @Expose
    val imageUrl: String? = null,
)


