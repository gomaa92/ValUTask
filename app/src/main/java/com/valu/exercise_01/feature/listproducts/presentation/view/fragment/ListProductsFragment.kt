package com.valu.exercise_01.feature.listproducts.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.valu.exercise_01.R
import com.valu.exercise_01.databinding.FragmentListProductsBinding
import com.valu.exercise_01.feature.listproducts.data.model.Product
import com.valu.exercise_01.feature.listproducts.presentation.view.adapter.ListProductsAdapter
import com.valu.exercise_01.feature.listproducts.presentation.viewmodel.ListProductsViewModel
import com.valu.exercise_01.feature.listproducts.presentation.viewmodel.ProductsContractor.ListProductsViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProductsFragment : Fragment(R.layout.fragment_list_products) {

    private val viewModel: ListProductsViewModel by viewModels()

    private var adapter: ListProductsAdapter? = null

    private var binding: FragmentListProductsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListProductsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        if (viewModel.getProductsLiveData().value == null)
            fetchData()

        observeOnProductsChange()

        binding?.noDataIv?.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData() {
        binding?.messagesProgressBar?.visibility = View.VISIBLE
        binding?.emptyGroup?.visibility = View.GONE
        viewModel.getProducts()
    }

    private fun initRecyclerView() {
        adapter = ListProductsAdapter { item ->
            navigateToProductDetails(item)
        }
        binding?.messageRecyclerView?.adapter = adapter
    }

    private fun hideProgressbar() {
        binding?.messagesProgressBar?.visibility = View.GONE
    }

    private fun observeOnProductsChange() {
        viewModel.getProductsLiveData().observe(viewLifecycleOwner) { state ->
            handleViewState(state)
        }
    }

    private fun handleViewState(state: ListProductsViewState) {
        binding?.emptyGroup?.visibility = View.GONE
        when (state) {
            ListProductsViewState.GetProductsEmpty -> {
                hideProgressbar()
                binding?.emptyGroup?.visibility = View.VISIBLE
            }
            ListProductsViewState.GetProductsFailed -> {
                hideProgressbar()
                handleFailure()
            }
            is ListProductsViewState.GetProductsSuccess -> {
                hideProgressbar()
                adapter?.submitList(state.products)
            }
        }
    }

    private fun handleFailure() {
        context?.let {
            val alertDialogBuilder = AlertDialog.Builder(it)
            alertDialogBuilder.apply {
                setTitle(getString(R.string.failed_to_fetch_messages_title))
                setMessage(getString(R.string.failed_to_fetch_messages_desc))
                setPositiveButton(getString(R.string.try_again)) { _, _ ->
                    fetchData()
                }
                setNegativeButton(getString(R.string.exit)) { _, _ ->
                    activity?.finish()
                }
                show()
            }
        }
    }

    private fun navigateToProductDetails(product: Product) {
        val action =
            ListProductsFragmentDirections.actionListProductsFragmentToProductDetailsFragment(
                product
            )
        findNavController().navigate(action)
    }
}