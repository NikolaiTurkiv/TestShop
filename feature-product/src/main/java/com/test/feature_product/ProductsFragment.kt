package com.test.feature_product

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.test.android_utils.viewBinding
import com.test.feature_product.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_product) {

    val viewModel by viewModels<ProductViewModel>()
    val binding by viewBinding<FragmentProductBinding>()

    private val adapter = ListDelegationAdapter(
        ProductsDelegate.categoryHorizontalDelegate
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvProducts.adapter = adapter
        initObservers()
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
                            title = "Latest",
                            list = it.latestList
                        ),
                        ProductsHorizontalItem(
                            title= "FlashSale",
                            list = it.flashSaleList
                        )

                    )
                    notifyDataSetChanged()
                }
            }

        }
    }
}
