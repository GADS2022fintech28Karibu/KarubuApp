package com.pauljuma.karibuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pauljuma.karibuapp.fragments.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container,LoginFragment())
            .commit()
    }
}