package com.pauljuma.karibuapp.onboards.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pauljuma.karibuapp.R
import com.pauljuma.karibuapp.databinding.FragmentViewPagerBinding
import com.pauljuma.karibuapp.onboards.FirstOnBoard
import com.pauljuma.karibuapp.onboards.SecondOnBoard
import com.pauljuma.karibuapp.onboards.ThirdOnBoard

lateinit var viewBinding: FragmentViewPagerBinding

class ViewPager : Fragment() {

    lateinit var viewBinding: FragmentViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewBinding = FragmentViewPagerBinding.inflate(inflater, container, false)
       // val view = inflater.inflate(R.layout.fragment_view_pager, container, false)
        val firstOnBoard = FirstOnBoard()
        val secondOnBoard = SecondOnBoard()
        val thirdOnBoard = ThirdOnBoard()

        val fragmentList = arrayListOf<Fragment>(firstOnBoard, secondOnBoard, thirdOnBoard)
        val adapter =
            ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        viewBinding.vpViewPager.adapter = adapter

        return viewBinding.root

    }
}