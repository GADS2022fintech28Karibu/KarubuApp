package com.pauljuma.karibuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.pauljuma.karibuapp.database.AppDatabase
import com.pauljuma.karibuapp.repository.KaribuRepository
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModel
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val karibuRepository = KaribuRepository(AppDatabase(this))
        val favoriteViewModelFactory = FavoriteViewModelFactory(karibuRepository)

        favoriteViewModel =
            ViewModelProvider(this, favoriteViewModelFactory).get(FavoriteViewModel::class.java)

        /*
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        BottomNavigationView.setupWithNavController(navController)
         */
    }
}