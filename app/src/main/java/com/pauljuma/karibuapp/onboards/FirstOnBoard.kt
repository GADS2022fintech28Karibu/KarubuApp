package com.pauljuma.karibuapp.onboards

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.pauljuma.karibuapp.R
import com.pauljuma.karibuapp.databinding.FragmentFirstOnBoardBinding

class FirstOnBoard : Fragment() {

    lateinit var viewBinding: FragmentFirstOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentFirstOnBoardBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.vpViewPager)

        viewBinding.btnNext1.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return viewBinding.root
    }

}