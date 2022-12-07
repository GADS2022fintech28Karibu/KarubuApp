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
import com.pauljuma.karibuapp.databinding.SeachRecycleviewBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    lateinit var binding: SeachRecycleviewBinding
    val item: MutableList<FavoriteMealsItem> = ArrayList()

    var listener: ((view: View, item: FavoriteMealsItem, position: Int) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun addFavoriteItems(data: List<FavoriteMealsItem>) {
        item.clear()
        item.addAll(data)
        notifyDataSetChanged()

    }

    inner class SearchViewHolder(val binding: SeachRecycleviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(favoriteMealsItem: FavoriteMealsItem, position: Int) {
            binding.apply {
                Glide.with(binding.root).load(favoriteMealsItem.imageUrl).diskCacheStrategy(
                    DiskCacheStrategy.NONE
                ).skipMemoryCache(true).signature(ObjectKey(System.currentTimeMillis()))
                    .into(ivSearchItem)
                tvsearchItemName.text = favoriteMealsItem.firstName
                this.tvSearchItemPrice.text = "KES ${favoriteMealsItem.price}"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        binding =
            SeachRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val items = item[position]
        holder.bind(items, position)
        holder.binding.btnAddToCart.setOnClickListener {
            listener?.invoke(holder.itemView, item[position], position)
            Toast.makeText(holder.itemView.context, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}