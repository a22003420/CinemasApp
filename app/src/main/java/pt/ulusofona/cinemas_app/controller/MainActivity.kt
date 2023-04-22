package pt.ulusofona.cinemas_app.controller

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.TextView
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
      R.id.navigation_microphone -> {
        val countdownDialog: Dialog = createCountdownDialog();
        countdownDialog.show();

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
