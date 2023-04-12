package View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cinemas_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        bottomNav.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_dashboard -> {
                    Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_movie_presentation -> {
                    Toast.makeText(this, "Cinemas", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_movie_register -> {
                    Toast.makeText(this, "Registo", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

    }

}
