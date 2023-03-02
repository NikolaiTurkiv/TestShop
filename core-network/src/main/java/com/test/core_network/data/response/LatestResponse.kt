package com.test.core_network.data.response

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.test.network.data.response.LatestResponseItem


data class LatestResponse(
    @SerializedName("latest")
    @Expose
    val latest: List<LatestResponseItem>? = null
)

