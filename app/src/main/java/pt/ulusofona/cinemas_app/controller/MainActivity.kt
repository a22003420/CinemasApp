package pt.ulusofona.cinemas_app.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding
  private val TAG = MainActivity::class.java.simpleName

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    NavigationManager.goToDashboardFragment(supportFragmentManager)
  }

  override fun onStart() {
    super.onStart()
    setupBottomMenu()
  }

  private fun setupBottomMenu() {
    binding.bottomNavigationView.setOnNavigationItemSelectedListener{
      onClickNavigationItem(it)
    }
  }

  private fun onClickNavigationItem(item: MenuItem): Boolean {
    when(item.itemId) {
      R.id.navigation_dashboard -> NavigationManager.goToDashboardFragment(supportFragmentManager)
      R.id.navigation_movie_presentation -> NavigationManager.goToFilmesFragment(
        supportFragmentManager
      )
      R.id.navigation_movie_register -> NavigationManager.goToRegistoFragment(supportFragmentManager)
    }
    return true

  }
}
