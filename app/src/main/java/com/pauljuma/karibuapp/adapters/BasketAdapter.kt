package com.pauljuma.karibuapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import com.pauljuma.karibuapp.data.CartItem
import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.databinding.BasketRecycleviewBinding


class BasketAdapter : RecyclerView.Adapter<BasketAdapter.BasketAdapterViewHolder>() {
    lateinit var binding: BasketRecycleviewBinding
    private var basketItem: MutableList<FavoriteMealsItem> = ArrayList()
    private var cartItem: MutableList<CartItem> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addToBasket(data: List<FavoriteMealsItem>, carts: List<CartItem>) {
        cartItem.clear()
        cartItem.addAll(carts)
        basketItem.clear()
        basketItem.addAll(data)
        notifyDataSetChanged()
    }

    inner class BasketAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(cartItem: CartItem, position: Int) {
            val item = basketItem.firstOrNull { it.id == cartItem.productId }
            binding.apply {
                this.tvBasketItemName.text = item?.firstName
                this.tvBasketItemAmount.text = "KES ${
                    (item?.price?.toFloat()
                        ?.times(cartItem.quantity.toFloat()))
                }"
                Glide.with(binding.root).load(item?.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true).signature(ObjectKey(System.currentTimeMillis()))
                    .into(ivBasketItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapterViewHolder {
        binding =
            BasketRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BasketAdapterViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: BasketAdapterViewHolder, position: Int) {
        val myBasketItem = cartItem[position]
        holder.bind(myBasketItem, position)
    }

    override fun getItemCount(): Int {
        return cartItem.size
    }
}