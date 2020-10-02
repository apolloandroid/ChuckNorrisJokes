package com.example.chucknorrisjokes

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navigationView: BottomNavigationView = findViewById(R.id.navigation_view)
        val navigationController = Navigation.findNavController(this, R.id.navigation_host_fragment)
        NavigationUI.setupWithNavController(navigationView, navigationController)

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragment_jokes, R.id.fragment_api_info)
        )

        setupActionBarWithNavController(navigationController, appBarConfiguration)
        navigationView.setupWithNavController(navigationController)

        navigationView.setOnNavigationItemSelectedListener { item ->
            navigationViewClickListener(item)
            false
        }
    }

    private fun navigationViewClickListener(item: MenuItem) {
        val navigationController = Navigation.findNavController(this, R.id.navigation_host_fragment)
        if (!item.isChecked)
            when (item.itemId) {
                R.id.navigation_jokes -> navigationController.navigate(R.id.fragment_jokes)
                R.id.navigation_web -> navigationController.navigate(R.id.fragment_api_info)
            }
        item.isChecked = true
    }
}