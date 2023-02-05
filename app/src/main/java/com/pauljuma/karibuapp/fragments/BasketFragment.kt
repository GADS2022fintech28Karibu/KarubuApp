package com.pauljuma.karibuapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pauljuma.karibuapp.HomeActivity
import com.pauljuma.karibuapp.MainActivity
import com.pauljuma.karibuapp.adapters.BasketAdapter
import com.pauljuma.karibuapp.databinding.FragmentBasketBinding
import com.pauljuma.karibuapp.viewmodel.CartViewModel

class BasketFragment() : Fragment() {

    lateinit var binding: FragmentBasketBinding
    private val basketAdapter: BasketAdapter by lazy { BasketAdapter(cartViewModel) }
    lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentBasketBinding.inflate(inflater, container, false)
        cartViewModel = (activity as HomeActivity).cartViewModel
        observeBasketFragment()

        return binding.root
    }

    fun setBasketRecycleView() {
        binding.rvBasketItems.apply {
            hasFixedSize()
            adapter = basketAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        basketAdapter.listener = { v, i, p ->
            cartViewModel.delete(i)
            cartViewModel.getCartItem()
            cartViewModel.favoriteMeals.value?.let {
                cartViewModel.cartItems.value?.let { it1 ->
                    basketAdapter.addToCart(
                        it,
                        it1
                    )
                }
            }
        }
    }


    fun observeBasketFragment() {
        cartViewModel.cartItems.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                cartViewModel.cartItems.value?.let { it1 ->
                    cartViewModel.favoriteMeals.value?.let { it2 ->
                        basketAdapter.addToCart(
                            it2, it1
                        )
                    }
                }
                setBasketRecycleView()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        cartViewModel.getCartItem()
    }

    fun getTotal() {
        binding.btnPay.setOnClickListener {
            val item = basketAdapter
        }
    }
}