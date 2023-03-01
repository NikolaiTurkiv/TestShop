package com.test.network.data

import com.test.core_network.data.response.FlashSaleResponse
import com.test.core_network.data.response.FlashSaleResponseItem
import com.test.core_network.data.response.LatestResponse
import com.test.network.data.response.LatestResponseItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface NetworkApi {
    companion object {
        private const val LATEST_PRODUCT = "cc0071a1-f06e-48fa-9e90-b1c2a61eaca7"
        private const val FLASH_SALE = "a9ceeb6e-416d-4352-bde6-2203416576ac"
    }

    @GET(LATEST_PRODUCT)
    fun getLatestProduct(): Single<LatestResponse>

    @GET(FLASH_SALE)
    fun getFlashSale(): Single<FlashSaleResponse>
}
