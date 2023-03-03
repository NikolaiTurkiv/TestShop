package com.test.feature_product.presentation

import android.util.Log
import com.mediapark.lib_android_base.mvi.MviAction
import com.mediapark.lib_android_base.mvi.MviViewState
import com.test.core_resources.R
import com.test.feature_product.domain.CategoryItem
import com.test.feature_product.domain.FlashSaleItem
import com.test.feature_product.domain.LatestItem
import com.test.lib_android_utils.BaseViewModel
import com.test.repository_products.domain.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase,
) : BaseViewModel<ProductViewModel.ViewState, ProductViewModel.Action>(
    ViewState()
) {

    val categoryList = listOf<CategoryItem>(
        CategoryItem(PHONES, R.drawable.ic_phone),
        CategoryItem(HEADPHONES, R.drawable.ic_headphones),
        CategoryItem(GAMES, R.drawable.ic_gamepad),
        CategoryItem(CARS, R.drawable.ic_car),
        CategoryItem(FURNITURE, R.drawable.ic_sleep),
        CategoryItem(KIDS, R.drawable.ic_robot),
    )


    init {
        getLatest()
        getFlashSale()
    }


    private fun getLatest() {
        productsUseCase.listLatest
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                val formatter: NumberFormat = NumberFormat.getInstance(Locale.US)
                formatter.maximumFractionDigits = 2

                sendAction(Action.LatestList(it.map { latest ->
                    LatestItem(
                        category = latest.category,
                        name = latest.name,
                        price = formatter.format(latest.price),
                        imageUrl = latest.imageUrl
                    )
                }))
            }, {
                Log.d(ERROR_REQUEST, it.message.toString())
            })
    }

    private fun getFlashSale() {
        productsUseCase.listFlashSale
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val formatter: NumberFormat = NumberFormat.getInstance(Locale.US)
                formatter.maximumFractionDigits = 2

                sendAction(Action.FlashSaleList(it.map { flashSale ->
                    FlashSaleItem(
                        category = flashSale.category,
                        name = flashSale.name,
                        price = formatter.format(flashSale.price),
                        discount = flashSale.discount,
                        imageUrl = flashSale.imageUrl
                    )
                }))
            }, {
                Log.d(ERROR_REQUEST, it.message.toString())
            })
    }


    data class ViewState(
        val latestList: List<LatestItem>? = null,
        val flashSaleList: List<FlashSaleItem>? = null,
        val error: String? = null,
    ) : MviViewState

    sealed class Action() : MviAction {
        object DoNothing : Action()
        data class FlashSaleList(val list: List<FlashSaleItem>) : Action()
        data class LatestList(val list: List<LatestItem>) : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState =
        when (viewAction) {
            is Action.DoNothing -> state.copy()
            is Action.FlashSaleList -> state.copy(flashSaleList = viewAction.list)
            is Action.LatestList -> state.copy(latestList = viewAction.list)
        }

    companion object {
        private const val PHONES = "phones"
        private const val HEADPHONES = "headphones"
        private const val GAMES = "games"
        private const val CARS = "cars"
        private const val FURNITURE = "furniture"
        private const val KIDS = "kids"
        private const val ERROR_REQUEST = "ERROR_REQUEST"
    }

}