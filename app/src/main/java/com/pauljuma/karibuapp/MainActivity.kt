package com.pauljuma.karibuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
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

        val karibuRepository = KaribuRepository(AppDatabase(this))
        val favoriteViewModelFactory = FavoriteViewModelFactory(karibuRepository)
        val cartViewModelFactory = CartViewModelFactory(karibuRepository)

        cartViewModel = ViewModelProvider(this, cartViewModelFactory).get(CartViewModel::class.java)

        favoriteViewModel =
            ViewModelProvider(this, favoriteViewModelFactory).get(FavoriteViewModel::class.java)

        /*
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        BottomNavigationView.setupWithNavController(navController)
         */
    }
}
