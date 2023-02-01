package com.pauljuma.karibuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pauljuma.karibuapp.database.AppDatabase
import com.pauljuma.karibuapp.repository.KaribuRepository
import com.pauljuma.karibuapp.viewmodel.CartViewModel
import com.pauljuma.karibuapp.viewmodel.CartViewModelFactory
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModel
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModelFactory


class HomeActivity : AppCompatActivity() {

    lateinit var favoriteViewModel: FavoriteViewModel
    lateinit var cartViewModel: CartViewModel
    lateinit var binding
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


       val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

       // binding.BottomNavigationView.setupWithNavController(navController)

    }
}
