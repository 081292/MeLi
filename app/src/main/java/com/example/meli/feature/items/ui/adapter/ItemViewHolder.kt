package com.example.meli.feature.items.ui.adapter

import android.util.Log
import android.view.View
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meli.databinding.ItemItemBinding
import com.example.meli.feature.items.ui.ItemUIModel

class ItemViewHolder(private val view: View, private val findNavController: NavController) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemItemBinding.bind(view)

    fun render(itemUIModel: ItemUIModel) {
        //binding.titleItem.text = itemUIModel.title
        binding.priceItem.text = "$" + itemUIModel.price
        Glide.with(binding.imageItem.context).load(itemUIModel.thumbnail).into(binding.imageItem)
        Log.d("TAG", itemUIModel.thumbnail)
    }
}
