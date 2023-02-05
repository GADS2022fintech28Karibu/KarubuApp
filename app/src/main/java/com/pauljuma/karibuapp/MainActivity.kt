package com.pauljuma.karibuapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.pauljuma.karibuapp.database.AppDatabase
import com.pauljuma.karibuapp.databinding.ActivityMainBinding
import com.pauljuma.karibuapp.repository.KaribuRepository
import com.pauljuma.karibuapp.viewmodel.CartViewModel
import com.pauljuma.karibuapp.viewmodel.CartViewModelFactory
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModel
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModelFactory


class MainActivity : AppCompatActivity() {

    lateinit var favoriteViewModel: FavoriteViewModel
    lateinit var cartViewModel: CartViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.hide()

       val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

       // binding.BottomNavigationView.setupWithNavController(navController)

    }
}
