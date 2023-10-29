package com.example.meli.feature.items.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.meli.databinding.FragmentItemsBinding
import com.example.meli.feature.items.ui.adapter.ItemAdapter
import com.example.meli.ui.view.base.Inflater
import com.example.meli.ui.view.base.MeLiBaseDataBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class ItemsFragment : MeLiBaseDataBindingFragment<FragmentItemsBinding, ItemsViewModel>(),
    SearchView.OnQueryTextListener {

    private val args: ItemsFragmentArgs by navArgs()

    override fun bindingInflater(): Inflater<FragmentItemsBinding> = FragmentItemsBinding::inflate

    override fun viewModelClass(): KClass<ItemsViewModel> = ItemsViewModel::class

    override val viewModel: ItemsViewModel by viewModels()

    private val itemUIModelList = mutableListOf<ItemUIModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })

        binding.browserField.setOnQueryTextListener(this)
        initRecyclerView(itemUIModelList)

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is ItemsState.Inactive -> {
                    Log.d("TAG", "Inactive")
                }

                is ItemsState.Loading -> {
                    Log.d("TAG", "Loading")
                }

                is ItemsState.ItemsModelState -> {
                    itemUIModelList.clear()
                    itemUIModelList.addAll(it.itemUIModelList)
                    binding.itemsRV.adapter?.notifyDataSetChanged()
                }

                is ItemsState.Error -> {
                    Log.d(
                        "TAG",
                        "Error{ Code: ${it.code.toString()} Message: ${it.error.toString()} }"
                    )
                }
            }
        }
    }

    private fun initRecyclerView(itemUIModelList: List<ItemUIModel>) {
        binding.itemsRV.layoutManager = GridLayoutManager(activity?.applicationContext, 2)
        binding.itemsRV.adapter = ItemAdapter(itemUIModelList, findNavController())
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            viewModel.sendAction(ItemsActions.FetchItems(args.siteId, query))
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
