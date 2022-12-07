package com.pauljuma.karibuapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.databinding.FavoritesRecycleviewBinding

class FavoriteMealsAdapter : RecyclerView.Adapter<FavoriteMealsAdapter.FavoriteMealsAdapterViewHolder>() {
    lateinit var binding: FavoritesRecycleviewBinding

    private val favoriteMeal: MutableList<FavoriteMealsItem> = ArrayList()

    var listener: ((view: View, item: FavoriteMealsItem, position: Int) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun addFavorites(data: List<FavoriteMealsItem>){
        favoriteMeal.clear()
        favoriteMeal.addAll(data)
        notifyDataSetChanged()
    }

    inner class FavoriteMealsAdapterViewHolder(val binding: FavoritesRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var currentPosition: Int = -1
        private var currentItem: FavoriteMealsItem? = null


        @SuppressLint("SetTextI18n")
        fun bind(favoriteMealsItem: FavoriteMealsItem, itemPosition: Int) {
            binding.apply {
                tvFavoritePrice.text = "KES ${favoriteMealsItem.price}"
                tvName.text = favoriteMealsItem.firstName
                Glide.with(binding.root).load(favoriteMealsItem.imageUrl).
                diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).signature(ObjectKey(System.currentTimeMillis())).into(ivFoodItem)
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
        holder.binding.btnAddToCart.setOnClickListener {
            listener?.invoke(holder.itemView, favoriteMeal[position], position)
            Toast.makeText(holder.itemView.context, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return favoriteMeal.size
    }
}