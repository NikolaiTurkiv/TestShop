package com.test.feature_product.presentation

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.test.android_utils.viewBinding
import com.test.feature_product.R
import com.test.feature_product.databinding.FragmentProductBinding
import com.test.feature_product.domain.ProductsHorizontalItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_product) {

    private val viewModel by viewModels<ProductViewModel>()
    private val binding by viewBinding<FragmentProductBinding>()

    private val adapter = ListDelegationAdapter(
        ProductsDelegate.categoryHorizontalDelegate
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvProducts.adapter = adapter
        initObservers()
        setTitle()
    }

    private fun initObservers() {
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            if (!it.flashSaleList.isNullOrEmpty() && !it.latestList.isNullOrEmpty()) {
                adapter.apply {
                    items = listOf(
                        ProductsHorizontalItem(
                            title = "",
                            list = viewModel.categoryList
                        ),
                        ProductsHorizontalItem(
                            title = LATEST,
                            list = it.latestList
                        ),
                        ProductsHorizontalItem(
                            title = FLASH_SALE,
                            list = it.flashSaleList
                        )

                    )
                    notifyDataSetChanged()
                }
            } else {
                adapter.apply {
                    items = listOf(
                        ProductsHorizontalItem(
                            title = "",
                            list = viewModel.categoryList
                        ),
                    )
                    notifyDataSetChanged()
                }
            }
        }
    }

    private fun setTitle() {
        binding.title.text = Html.fromHtml(TITLE)
    }

    companion object{
        private const val TITLE = "<font color=#000000>Trade by</font> <font color=#4E55D7>bata</font>"
        private const val LATEST = "Latest"
        private const val FLASH_SALE = "FlashSale"
    }

}
