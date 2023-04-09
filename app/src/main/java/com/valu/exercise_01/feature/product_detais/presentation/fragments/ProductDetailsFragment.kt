package com.valu.exercise_01.feature.product_detais.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.valu.exercise_01.R
import com.valu.exercise_01.base.persentation.utils.Utils
import com.valu.exercise_01.databinding.FragmentProductDetailsBinding
import com.valu.exercise_01.feature.listproducts.data.model.Product
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    private var binding: FragmentProductDetailsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Product>(PRODUCT_KEY)?.let { product ->
            bindData(product)
        }

    }

    private fun bindData(product: Product) {
        binding?.productRating?.rating = product.rating.rate.toFloat()
        Utils.loadImage(
            this.requireContext(),
            binding?.imageView,
            product.image
        )
        binding?.productTitle?.text = product.title
        binding?.productPrice?.text = "${product.price} EGP"

        binding?.productDescription?.text = product.description
    }

    companion object {
        const val PRODUCT_KEY = "product_key"
    }
}
