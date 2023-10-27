package com.example.meli.feature.sites.ui.adapter

import android.view.View
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.meli.R
import com.example.meli.databinding.ItemSiteBinding
import com.example.meli.feature.sites.ui.SiteUIModel
import com.example.meli.feature.sites.ui.SitesFragmentDirections

class SiteViewHolder(private val view: View, private val findNavController: NavController) :
    RecyclerView.ViewHolder(view) {

    private val binding = ItemSiteBinding.bind(view)

    fun render(siteUIModel: SiteUIModel) {
        binding.siteName.text = siteUIModel.name
        itemView.setOnClickListener {
            findNavController.navigate(
                SitesFragmentDirections.actionSitesFragmentToItemsFragment(
                    siteId = siteUIModel.id
                )
            )
        }
    }
}
