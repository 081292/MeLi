package com.example.meli.feature.sites.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.meli.R
import com.example.meli.feature.sites.ui.SiteUIModel

class SiteAdapter(private val siteUIModelList: List<SiteUIModel>, private val findNavController: NavController) : RecyclerView.Adapter<SiteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SiteViewHolder(layoutInflater.inflate(R.layout.item_site, parent, false), findNavController)
    }

    override fun getItemCount(): Int = siteUIModelList.size

    override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
        val item = siteUIModelList[position]
        holder.render(item)
    }
}
