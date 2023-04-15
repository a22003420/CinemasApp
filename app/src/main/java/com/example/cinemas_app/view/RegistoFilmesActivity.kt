package com.example.cinemas_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cinemas_app.R
import com.example.cinemas_app.view.fragments.RegistoFilmesFragment

class RegistoFilmesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registo_filmes)

        supportFragmentManager.beginTransaction()
            .add(R.id.registo_filmes_layout, RegistoFilmesFragment())
            .commit()
    }
}
