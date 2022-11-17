package com.pauljuma.karibuapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.databinding.FavoritesRecycleviewBinding

class FavoriteMealsAdapter : RecyclerView.Adapter<FavoriteMealsAdapter.FavoriteMealsAdapterViewHolder>() {
    lateinit var binding: FavoritesRecycleviewBinding

    private val favoriteMeal: MutableList<FavoriteMealsItem> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addFavorites(data: List<FavoriteMealsItem>){
        favoriteMeal.clear()
        favoriteMeal.addAll(data)
        notifyDataSetChanged()
    }

    inner class FavoriteMealsAdapterViewHolder(binding: FavoritesRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var currentPosition: Int = -1
        private var currentItem: FavoriteMealsItem? = null

        fun bind(favoriteMealsItem: FavoriteMealsItem, itemPosition: Int) {
            binding.apply {
                tvName.text = favoriteMealsItem.firstName
                Glide.with(binding.root).load(favoriteMealsItem.imageUrl).into(ivFoodItem)
            }

            this.currentItem = favoriteMealsItem
            this.currentPosition = itemPosition
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMealsAdapterViewHolder {
        binding =
            FavoritesRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FavoriteMealsAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMealsAdapterViewHolder, position: Int) {
        val favoriteItem = favoriteMeal[position]
        holder.bind(favoriteItem, position)
    }

    override fun getItemCount(): Int {
        return favoriteMeal.size
    }
}