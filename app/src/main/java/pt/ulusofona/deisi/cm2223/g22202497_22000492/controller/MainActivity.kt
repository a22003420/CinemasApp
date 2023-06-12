package pt.ulusofona.deisi.cm2223.g22202497_22000492.controller

import android.app.Dialog
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.TextView
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.ActivityMainBinding
import android.Manifest
import android.view.View
import com.fondesa.kpermissions.allGranted
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.extension.send


class MainActivity : AppCompatActivity() {
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    permissionsBuilder(
      Manifest.permission.ACCESS_FINE_LOCATION,
      Manifest.permission.ACCESS_COARSE_LOCATION).build().send { result ->
      if (result.allGranted()) {
        NavigationManager.goToDashboardFragment(
          supportFragmentManager,
          binding.bottomNavigationView,
          resources.getColor(R.color.wine_red, null)
        )
      } else {
        finish()
      }
    }
  }

  override fun onStart() {
    super.onStart()
    setupBottomMenu()
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)
  }

  private fun setupBottomMenu() {
    binding.bottomNavigationView.setOnNavigationItemSelectedListener{
      onClickNavigationItem(it)
    }
  }

  private fun onClickNavigationItem(item: MenuItem): Boolean {
    val highlightedColor = resources.getColor(R.color.wine_red, null)
    when(item.itemId) {
      R.id.navigation_dashboard -> {
        NavigationManager.goToDashboardFragment(
          supportFragmentManager, binding.bottomNavigationView, highlightedColor
        )
      }
      R.id.navigation_movie_presentation -> {
        NavigationManager.goToFilmesFragment(
          supportFragmentManager, binding.bottomNavigationView, highlightedColor
        )
      }
      R.id.navigation_movie_register -> {
        NavigationManager.goToRegistoFragment(
          supportFragmentManager, binding.bottomNavigationView, highlightedColor
        )
      }
      R.id.navigation_microphone -> {
        val countdownDialog: Dialog = createCountdownDialog();
        countdownDialog.show()

      }
    }
    return true
  }

  private fun createCountdownDialog(): Dialog {
    val dialog = Dialog(this)
    dialog.setContentView(R.layout.voice_search_dialog)

    val countdownText = dialog.findViewById<TextView>(R.id.countdown_text)
    countdownText.text = "10"

    val handler = Handler()
    handler.postDelayed(object : Runnable {
      override fun run() {
        var countdown = countdownText.text.toString().toInt()
        countdown--
        if (countdown == 0) {
          dialog.dismiss()
        } else {
          countdownText.text = countdown.toString()
          handler.postDelayed(this, 1000)
        }
      }
    }, 1000)
    return dialog
  }
}