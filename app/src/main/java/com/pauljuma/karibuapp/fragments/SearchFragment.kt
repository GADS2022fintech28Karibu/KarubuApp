package com.pauljuma.karibuapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.pauljuma.karibuapp.MainActivity
import com.pauljuma.karibuapp.R
import com.pauljuma.karibuapp.adapters.SearchAdapter
import com.pauljuma.karibuapp.data.CartItem
import com.pauljuma.karibuapp.databinding.FragmentSearchBinding
import com.pauljuma.karibuapp.viewmodel.CartViewModel
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_search.rvSearch

class SearchFragment : Fragment() {
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter() }
    lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: FavoriteViewModel
    lateinit var cartViewModel: CartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        viewModel = (activity as MainActivity).favoriteViewModel
        cartViewModel = (activity as MainActivity).cartViewModel

        observeSearchRecycleview()

        return binding.root
    }

    fun setUpSearchRecycleView() {
        rvSearch.apply {
            hasFixedSize()
            adapter = searchAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        searchAdapter.listener = {v, i, p ->
            val cartItem2 = CartItem(i.id, i.id, 1)
            cartViewModel.addToCart(cartItem2)
        }
    }

    fun observeSearchRecycleview(){
        viewModel.favoriteMeals.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                searchAdapter.addFavoriteItems(it)
                setUpSearchRecycleView()
            }
        }
    }
}