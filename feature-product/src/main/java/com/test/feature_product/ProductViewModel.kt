package com.test.feature_product

import android.content.Context
import android.util.Log
import android.view.View
import com.mediapark.lib_android_base.mvi.MviAction
import com.mediapark.lib_android_base.mvi.MviViewState
import com.test.lib_android_utils.BaseViewModel
import com.test.repository_products.domain.FlashSaleResult
import com.test.repository_products.domain.LatestResult
import com.test.repository_products.domain.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase,
) : BaseViewModel<ProductViewModel.ViewState, ProductViewModel.Action>(
    ViewState()
) {

    val categoryList = listOf<CategoryItem>(
        CategoryItem(PHONES, com.test.core_resources.R.drawable.ic_phone),
        CategoryItem(HEADPHONES, com.test.core_resources.R.drawable.ic_headphones),
        CategoryItem(GAMES, com.test.core_resources.R.drawable.ic_gamepad),
        CategoryItem(CARS, com.test.core_resources.R.drawable.ic_car),
        CategoryItem(FURNITURE, com.test.core_resources.R.drawable.ic_sleep),
        CategoryItem(KIDS, com.test.core_resources.R.drawable.ic_robot),
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
                Log.d("LIST_PRODUCTS", it.toString())
                sendAction(Action.LatestList(it.map { latest ->
                    LatestItem(
                        category = latest.category,
                        name = latest.name,
                        price = latest.price,
                        imageUrl = latest.imageUrl
                    )
                }))
            }, {
                Log.d("ERROR", it.message.toString())
            })
    }

    private fun getFlashSale() {
        productsUseCase.listFlashSale
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("LIST_PRODUCTS", it.toString())
                sendAction(Action.FlashSaleList(it.map { flashSale ->
                    FlashSaleItem(
                        category = flashSale.category,
                        name = flashSale.name,
                        price = flashSale.price,
                        discount = flashSale.discount,
                        imageUrl = flashSale.imageUrl
                    )
                }))
            }, {
                Log.d("ERROR", it.message.toString())
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
    }

}