package com.pauljuma.karibuapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pauljuma.karibuapp.MainActivity
import com.pauljuma.karibuapp.R
import com.pauljuma.karibuapp.adapters.FavoriteMealsAdapter
import com.pauljuma.karibuapp.adapters.FeaturedPartnersAdapter
import com.pauljuma.karibuapp.data.CartItem
import com.pauljuma.karibuapp.databinding.FragmentHomeBinding
import com.pauljuma.karibuapp.viewmodel.CartViewModel
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.favorites_recycleview.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private val featuredPartnerAdapter: FeaturedPartnersAdapter by lazy { FeaturedPartnersAdapter() }
    private val favoriteMealsAdapter: FavoriteMealsAdapter by lazy { FavoriteMealsAdapter() }

    //lateinit var favoriteMealsAdapter: FavoriteMealsAdapter
    private lateinit var viewBinding: FragmentHomeBinding
    lateinit var favoriteViewModel: FavoriteViewModel
    lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentHomeBinding.inflate(inflater, container, false)


        favoriteViewModel = (activity as MainActivity).favoriteViewModel
        cartViewModel = (activity as MainActivity).cartViewModel
        observeFeaturedPartners()
        observeFavoriteMeals()

        viewBinding.tvSearch.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        val appCompat = requireActivity() as AppCompatActivity
        val navHostFragment =
            appCompat.supportFragmentManager.findFragmentById(R.id.fcFragmentContainer) as NavHostFragment

        val navController = navHostFragment.navController

        viewBinding.BottomNavigationView.setupWithNavController(navController)

        viewBinding.BottomNavigationView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_basketFragment2)
        }
        viewBinding.ivSlides.setOnClickListener { v ->
            (activity as MainActivity).openCloseNavigationDrawer(v)
        }

        return viewBinding.root
    }

    fun observeFeaturedPartners() {
        favoriteViewModel.featuredPartners.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                featuredPartnerAdapter.addItem(it)
                setUpFeaturedPartnerRecycleView()

            }
        }
    }

    fun observeFavoriteMeals() {
        favoriteViewModel.favoriteMeals.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                favoriteMealsAdapter.addFavorites(it)
                setUpFavoriteMealsRecycleview()
            }
        }
    }

    fun setUpFeaturedPartnerRecycleView() {
        rvPopularDishes.apply {
            hasFixedSize()
            adapter = featuredPartnerAdapter

            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    fun setUpFavoriteMealsRecycleview() {
        rvFavoriteMeals.apply {
            hasFixedSize()
            adapter = favoriteMealsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        }
        favoriteMealsAdapter.listener = {v, i, p ->
            val cartItem = CartItem(i.id, i.id, 1)
            cartViewModel.addToCart(cartItem)

        }
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}