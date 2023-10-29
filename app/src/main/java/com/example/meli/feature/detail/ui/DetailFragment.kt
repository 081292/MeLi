package com.example.meli.feature.detail.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.meli.databinding.FragmentDetailBinding
import com.example.meli.ui.view.base.Inflater
import com.example.meli.ui.view.base.MeLiBaseDataBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class DetailFragment : MeLiBaseDataBindingFragment<FragmentDetailBinding, DetailViewModel>() {

    private val args: DetailFragmentArgs by navArgs()

    override fun bindingInflater(): Inflater<FragmentDetailBinding> = FragmentDetailBinding::inflate

    override fun viewModelClass(): KClass<DetailViewModel> = DetailViewModel::class

    override val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.sendAction(DetailActions.FetchItem(args.itemId))

        viewModel.viewState.observe(viewLifecycleOwner) {
            when (it) {
                is DetailState.Inactive -> {
                    Log.d("TAG", "Inactive")
                }

                is DetailState.Loading -> {
                    Log.d("TAG", "Loading")
                }

                is DetailState.DetailModelState -> {
                    binding.detailTitle.text = it.detailUIModel.id
                    activity?.applicationContext?.let { context ->
                        Glide.with(context)
                            .load(it.detailUIModel.pictures.first().url)
                            .into(binding.detailImage)
                    }
                    binding.detailPrice.text = "$" + it.detailUIModel.price
                }

                is DetailState.Error -> {
                    Log.d(
                        "TAG",
                        "Error{ Code: ${it.code.toString()} Message: ${it.error.toString()} }"
                    )
                }
            }
        }
    }
}
