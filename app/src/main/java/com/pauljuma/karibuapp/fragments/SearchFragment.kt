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
import com.pauljuma.karibuapp.databinding.FragmentSearchBinding
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_search.rvSearch

class SearchFragment : Fragment() {
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter() }
    lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        viewModel = (activity as MainActivity).favoriteViewModel

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