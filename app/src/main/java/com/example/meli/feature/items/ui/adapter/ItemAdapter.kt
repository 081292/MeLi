package com.example.meli.feature.items.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.meli.R
import com.example.meli.feature.items.ui.ItemUIModel

class ItemAdapter(private val itemUIModelList: List<ItemUIModel>, private val findNavController: NavController) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(layoutInflater.inflate(R.layout.item_item, parent, false), findNavController)
    }

    override fun getItemCount(): Int = itemUIModelList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemUIModelList[position]
        holder.render(item)
    }
}
