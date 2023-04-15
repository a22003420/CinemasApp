package com.example.cinemas_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cinemas_app.R
import com.example.cinemas_app.view.fragments.RegistoFilmesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_dashboard -> {
                    Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_movie_presentation -> {
                    Toast.makeText(this, "Cinemas", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_movie_register -> {
                    val fragment = RegistoFilmesFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                    true
                }
                else -> false
            }
        }

    }
}

