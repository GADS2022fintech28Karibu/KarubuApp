package com.pauljuma.karibuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.pauljuma.karibuapp.database.AppDatabase
import com.pauljuma.karibuapp.repository.KaribuRepository
import com.pauljuma.karibuapp.viewmodel.CartViewModel
import com.pauljuma.karibuapp.viewmodel.CartViewModelFactory
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModel
import com.pauljuma.karibuapp.viewmodel.FavoriteViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.drawer_layout


    
class MainActivity : AppCompatActivity() {

    lateinit var favoriteViewModel: FavoriteViewModel
    lateinit var cartViewModel: CartViewModel
  
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    fun openCloseNavigationDrawer(view: View){
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }
}
