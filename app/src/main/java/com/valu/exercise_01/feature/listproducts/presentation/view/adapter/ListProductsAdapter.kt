package com.valu.exercise_01.feature.listproducts.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import com.valu.exercise_01.base.persentation.adapter.BaseListAdapter
import com.valu.exercise_01.base.persentation.utils.Utils
import com.valu.exercise_01.databinding.ItemProductBinding
import com.valu.exercise_01.feature.listproducts.data.model.Product

class ListProductsAdapter(private val onItemClickedListener: ((item: Product) -> Unit)) :
    BaseListAdapter<Product>(callback = ProductsDiffCallback()) {

    override fun createBinding(parent: ViewGroup, viewType: Int) =
        ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

    override fun bind(binding: ViewBinding, position: Int) {
        val itemViewBinding = binding as ItemProductBinding
        val item = getItem(position)
        Utils.loadImage(
            binding.root.context,
            itemViewBinding.imageView,
            item.image
        )
        itemViewBinding.titleTextView.text = item.title
        itemViewBinding.priceTextView.text = "${item.price} EGP"

        itemViewBinding.root.setOnClickListener {
            onItemClickedListener(item)
        }
    }
}

class ProductsDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) =
        oldItem.image == newItem.image

    override fun areContentsTheSame(oldItem: Product, newItem: Product) =
        oldItem.image == newItem.image && oldItem.title == newItem.title
}