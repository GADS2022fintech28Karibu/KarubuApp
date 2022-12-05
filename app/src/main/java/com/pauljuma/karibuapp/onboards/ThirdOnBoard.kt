package com.pauljuma.karibuapp.onboards

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.pauljuma.karibuapp.R
import com.pauljuma.karibuapp.databinding.FragmentThirdOnBoardBinding

class ThirdOnBoard : Fragment() {

    lateinit var viewBinding: FragmentThirdOnBoardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewBinding = FragmentThirdOnBoardBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.vpViewPager)

        viewBinding.btnNext3.setOnClickListener {
            viewPager?.currentItem = 3

            findNavController().navigate(R.id.action_viewPager_to_loginFragment)
            onBoardingFinished()
        }

        return viewBinding.root
    }

    private fun onBoardingFinished() {
        val sharedPref =
            requireActivity().getSharedPreferences("onBoardingFinished", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}