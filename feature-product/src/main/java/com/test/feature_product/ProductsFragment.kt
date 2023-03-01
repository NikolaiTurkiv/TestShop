package com.test.feature_product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.test.android_utils.viewBinding
import com.test.feature_product.databinding.FragmentProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_product) {

    val viewModel by viewModels<ProductViewModel>()
    val binding by viewBinding<FragmentProductBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers(){
        viewModel.stateLiveData.observe(viewLifecycleOwner){

        }
    }
}
