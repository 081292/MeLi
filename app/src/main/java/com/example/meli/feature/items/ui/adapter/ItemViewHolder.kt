package com.example.meli.feature.items.ui.adapter

import android.view.View
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.meli.databinding.ItemItemBinding
import com.example.meli.feature.items.ui.ItemUIModel
import com.example.meli.feature.items.ui.ItemsFragmentDirections

class ItemViewHolder(private val view: View, private val findNavController: NavController) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemItemBinding.bind(view)

    fun render(itemUIModel: ItemUIModel) {
        binding.priceItem.text = "$" + itemUIModel.price
        Glide.with(binding.imageItem.context).load(itemUIModel.thumbnail).into(binding.imageItem)
        itemView.setOnClickListener {
            findNavController.navigate(
                ItemsFragmentDirections.actionItemsFragmentToDetailFragment(
                    itemId = itemUIModel.id
                )
            )
        }
    }
}
