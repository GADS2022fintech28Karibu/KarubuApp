package com.pauljuma.karibuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.pauljuma.karibuapp.database.AppDatabase
import com.pauljuma.karibuapp.repository.KaribuRepository
import com.pauljuma.karibuapp.viewmodel.KaribuViewModel
import com.pauljuma.karibuapp.viewmodel.KaribuViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {

    lateinit var karibuViewModel: KaribuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val karibuRepository = KaribuRepository(AppDatabase(this))
        val karibuViewModelFactory = KaribuViewModelFactory(karibuRepository)

        karibuViewModel =
            ViewModelProvider(this, karibuViewModelFactory).get(KaribuViewModel::class.java)

        /*
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcFragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        BottomNavigationView.setupWithNavController(navController)
         */
    }
}