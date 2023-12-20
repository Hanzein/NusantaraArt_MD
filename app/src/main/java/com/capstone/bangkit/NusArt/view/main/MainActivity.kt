package com.capstone.bangkit.NusArt.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.capstone.bangkit.NusArt.R
import com.capstone.bangkit.NusArt.databinding.ActivityMainBinding
import com.capstone.bangkit.NusArt.view.fragment.home.HomeFragment
import com.capstone.bangkit.NusArt.view.fragment.map.MapsFragment
import com.capstone.bangkit.NusArt.view.fragment.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navigation : BottomNavigationView = findViewById(R.id.bottom_navigation)
        navigation.setOnItemSelectedListener(itemSelectedListener)
    }

    private val itemSelectedListener = NavigationBarView.OnItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
//                val fragment = HomeFragment()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.ini_activity_main, fragment, fragment::class.java.simpleName)
//                return@OnItemSelectedListener true
                startActivity(Intent(this, MainActivity::class.java))
            }

            R.id.nav_map-> {
                val fragment = MapsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.ini_activity_main, fragment, fragment::class.java.simpleName)
                    .commit()
                return@OnItemSelectedListener true
            }

            R.id.nav_profile -> {
                val fragment = ProfileFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.ini_activity_main, fragment, fragment::class.java.simpleName)
                    .commit()
                return@OnItemSelectedListener true
            }
        }
        false
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}
