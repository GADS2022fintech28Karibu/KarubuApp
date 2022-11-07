package com.pauljuma.karibuapp.onboards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.pauljuma.karibuapp.R
import com.pauljuma.karibuapp.databinding.FragmentSecondOnBoardBinding

class SecondOnBoard : Fragment() {

    lateinit var viewBinding: FragmentSecondOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_second_on_board, container, false)
        viewBinding = FragmentSecondOnBoardBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.vpViewPager)
        viewBinding.btnNext2.setOnClickListener {
            viewPager?.currentItem = 2
        }
        return viewBinding.root
    }

}