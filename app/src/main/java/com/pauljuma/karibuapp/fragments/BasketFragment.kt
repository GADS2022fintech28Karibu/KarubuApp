package com.pauljuma.karibuapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pauljuma.karibuapp.MainActivity
import com.pauljuma.karibuapp.adapters.BasketAdapter
import com.pauljuma.karibuapp.databinding.FragmentBasketBinding
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_basket.*

class BasketFragment() : Fragment() {

    lateinit var binding: FragmentBasketBinding
    private val basketAdapter: BasketAdapter by lazy { BasketAdapter() }
    lateinit var favoriteViewModel: FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentBasketBinding.inflate(inflater, container, false)

        favoriteViewModel = (activity as MainActivity).favoriteViewModel
        observeBasketFragment()
        binding
        return binding.root
    }

    fun setBasketRecycleView(){
        rvBasketItems.apply {
            hasFixedSize()
            adapter = basketAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false )
        }
    }

    fun observeBasketFragment(){
        favoriteViewModel.favoriteMeals.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                favoriteViewModel.cartItem.value?.let { it1 -> basketAdapter.addToBasket(it, it1) }
                setBasketRecycleView()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.getCartItem()
    }
}