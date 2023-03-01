package com.test.feature_product

import android.util.Log
import android.view.View
import com.mediapark.lib_android_base.mvi.MviAction
import com.mediapark.lib_android_base.mvi.MviViewState
import com.test.lib_android_utils.BaseViewModel
import com.test.repository_products.domain.FlashSaleResult
import com.test.repository_products.domain.LatestResult
import com.test.repository_products.domain.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase
) : BaseViewModel<ProductViewModel.ViewState, ProductViewModel.Action>(
    ViewState()
) {


    init {
        getLatest()
        getFlashSale()
    }


    fun getLatest() {
        productsUseCase.listLatest
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("LIST_PRODUCTS", it.toString())
            }, {
                Log.d("ERROR", it.message.toString())
            })
    }

    fun getFlashSale() {
        productsUseCase.listFlashSale
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("LIST_PRODUCTS", it.toString())
            }, {
                Log.d("ERROR", it.message.toString())
            })
    }

    data class ViewState(
        val latestList: List<LatestResult>? = null,
        val flashSaleList: List<FlashSaleResult>? = null,
        val error: String? = null,
    ) : MviViewState

    sealed class Action() : MviAction {
        object DoNothing : Action()
    }

    override fun onReduceState(viewAction: Action): ViewState =
        when (viewAction) {
            is Action.DoNothing -> state.copy()
        }

}