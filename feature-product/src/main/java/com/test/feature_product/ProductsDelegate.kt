package com.test.feature_product

import android.util.Log
import android.view.View
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.squareup.picasso.Picasso
import com.test.feature_product.databinding.FlashSaleItemBinding
import com.test.feature_product.databinding.LatestItemBinding
import com.test.feature_product.databinding.ProductsHorizontalBinding
import com.test.feature_product.databinding.SelectCategoryItemBinding

object ProductsDelegate {

    val categoryHorizontalDelegate =
        adapterDelegateViewBinding<ProductsHorizontalItem, ListItem, ProductsHorizontalBinding>(
            { inflater, container ->
                ProductsHorizontalBinding.inflate(inflater, container, false).apply {
                    rv.adapter = ListDelegationAdapter(
                        categoryDelegateAdapter,
                        latestDelegateAdapter,
                        flashSaleDelegateAdapter
                    )
                }
            }) {
            bind {
                Log.d("DELEGATE", item.list.toString())
                (binding.rv.adapter as ListDelegationAdapter<List<ListItem>>).apply {
                    items = item.list
                    if(item.title.isNotEmpty()){
                        binding.titles.visibility = View.VISIBLE
                        binding.tvViewAll.visibility = View.VISIBLE
                        binding.titles.text = item.title
                    }else{
                        binding.titles.visibility = View.INVISIBLE
                        binding.tvViewAll.visibility = View.INVISIBLE
                    }
                    notifyDataSetChanged()
                }
            }
        }

    private val categoryDelegateAdapter =
        adapterDelegateViewBinding<CategoryItem, ListItem, SelectCategoryItemBinding>({ inflater, container ->
            SelectCategoryItemBinding.inflate(inflater, container, false)
        }) {
            bind {
                binding.categoryTitle.text = item.name
                binding.icon.setBackgroundResource(item.pic)
            }
        }

    private val latestDelegateAdapter =
        adapterDelegateViewBinding<LatestItem, ListItem, LatestItemBinding>({ inflater, container ->
            LatestItemBinding.inflate(inflater, container, false)
        }) {
            bind {
                binding.tvCategory.text = item.category
                binding.tvProductName.text = item.name
                binding.tvPrice.text = item.price.toString()

                Picasso.get().load(item.imageUrl).into(binding.itemBackground)

            }
        }


    private val flashSaleDelegateAdapter =
        adapterDelegateViewBinding<FlashSaleItem, ListItem, FlashSaleItemBinding>({ inflater, container ->
            FlashSaleItemBinding.inflate(inflater, container, false)
        }) {
            bind {
                binding.tvCategoryFalsh.text = item.category
                binding.tvProductNameFlash.text = item.name
                binding.tvPriceFlash.text = item.price.toString()
                binding.tvSale.text = item.discount.toString()

                Picasso.get().load(item.imageUrl).into(binding.itemBackgroundFlash)
            }
        }


    private val horizontalAdapter = ListDelegationAdapter(
        categoryDelegateAdapter,
        latestDelegateAdapter,
        flashSaleDelegateAdapter
    )

}
