package com.pauljuma.karibuapp.onboards

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.pauljuma.karibuapp.R


class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        Handler().postDelayed(
            {
               if (onBoardFinished()) {
                  findNavController().navigate(R.id.action_splashScreen_to_loginFragment)
               }
                else{
                  findNavController().navigate(R.id.action_splashScreen_to_viewPager)
                } }, 1000)
        return view
    }

    private fun onBoardFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoardingFinished", Context.MODE_PRIVATE)

        return sharedPref.getBoolean("Finished", false)
    }

}