package com.pauljuma.karibuapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pauljuma.karibuapp.R
import com.pauljuma.karibuapp.data.FeaturedPartnersItem
import com.pauljuma.karibuapp.databinding.FeaturedPartnerRecycleviewBinding
import com.squareup.picasso.Picasso

class FeaturedPartnersAdapter:
    RecyclerView.Adapter<FeaturedPartnersAdapter.FeaturedPartnerViewHolder>() {

    lateinit var binding: FeaturedPartnerRecycleviewBinding

    private val items: MutableList<FeaturedPartnersItem> = ArrayList()

    private val featuredPartnersImage = listOf(
        R.drawable.burger_inn,
        R.drawable.ice_cream_inn,
        R.drawable.pizza_in,
        R.drawable.dinner_inn
    )

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(data: List<FeaturedPartnersItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class FeaturedPartnerViewHolder(binding: FeaturedPartnerRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(featuredPartnersItem: FeaturedPartnersItem, featuredPartnerImage: List<Int>) {
            binding.apply {
                val imageId = (Math.random() * featuredPartnersImage.size).toInt()
                tvPartnerName.text = featuredPartnersItem.name
                Picasso.get().load(featuredPartnerImage[imageId]).into(binding.ivFeaturedPartners)

               /* root.setOnClickListener {  view->
                   view.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
                }*/
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedPartnerViewHolder {
        binding = FeaturedPartnerRecycleviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FeaturedPartnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeaturedPartnerViewHolder, position: Int) {
        holder.bind(items[position], featuredPartnersImage)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}