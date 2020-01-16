package com.example.themoviedb.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.themoviedb.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up navigation bar
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment

        findViewById<BottomNavigationView>(R.id.bottom_navigation)?.let { bottomNavView ->
            NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)
        }

        //setting the Bottom navigation visibiliy
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.MoviePosterScreenFragment || destination.id == R.id.PeoplePosterScreenFragment){
                bottom_navigation.visibility = View.GONE
            }else{
                bottom_navigation.visibility = View.VISIBLE
            }
        }
    }
}
