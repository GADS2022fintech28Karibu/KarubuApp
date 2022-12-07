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
import com.pauljuma.karibuapp.data.CartItem
import com.pauljuma.karibuapp.data.FavoriteMealsItem
import com.pauljuma.karibuapp.databinding.BasketRecycleviewBinding
import com.pauljuma.karibuapp.viewmodel.CartViewModel


class BasketAdapter(private val viewModel: CartViewModel) :
    RecyclerView.Adapter<BasketAdapter.BasketAdapterViewHolder>() {
    lateinit var binding: BasketRecycleviewBinding
    private var basketItem: MutableList<FavoriteMealsItem> = ArrayList()
    private var cartItem: MutableList<CartItem> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun addToCart(data: List<FavoriteMealsItem>, carts: List<CartItem>) {
        cartItem.clear()
        cartItem.addAll(carts)
        basketItem.clear()
        basketItem.addAll(data)
        notifyDataSetChanged()
    }

    inner class BasketAdapterViewHolder(val binding: BasketRecycleviewBinding) : RecyclerView.ViewHolder(binding.root) {
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

    var listener: ((view: View, item: CartItem, position: Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketAdapterViewHolder {
        binding =
            BasketRecycleviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return BasketAdapterViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BasketAdapterViewHolder, position: Int) {
        val quantity = binding.tvQuantity
        val myBasketItem = cartItem[position]
        holder.bind(myBasketItem, position)
        holder.binding.tvQuantity.text = "{${myBasketItem.quantity}}"
        //holder.itemView.tvQuantity.text = "{${myBasketItem.quantity}}"
        //holder.itemView.btnDelete.setOnClickListener
        holder.binding.btnDelete.setOnClickListener{
            removeItem(it, position)
            Toast.makeText(holder.itemView.context, "Removed", Toast.LENGTH_SHORT).show()
        }

        holder.binding.ivAdd.setOnClickListener {
            myBasketItem.quantity++
            viewModel.addToCart(myBasketItem)
        }

        holder.binding.ivSubtract.setOnClickListener {
            myBasketItem.quantity--
            viewModel.addToCart(myBasketItem)
        }
    }

    override fun getItemCount(): Int {
        return cartItem.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(view: View, position: Int) {
        listener?.invoke(view, cartItem[position], position)
        cartItem.removeAt(position)
        notifyDataSetChanged()
    }

    fun addQuantity() {
        cartItem
    }
}