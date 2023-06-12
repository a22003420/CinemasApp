package pt.ulusofona.deisi.cm2223.g22202497_22000492.controller

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments.FilmesFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.cinemas_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments.FilmesDetailFragment
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments.DashboardFragment
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments.RegistoFilmesFragment
import java.security.AccessController.getContext

object NavigationManager {

  private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
    val transition = fm.beginTransaction()
    transition.replace(R.id.fragment_container, fragment)
    transition.addToBackStack(null)
    transition.commit()
  }

  fun goToDashboardFragment(
    fm: FragmentManager,
    bottomNavigationView: BottomNavigationView,
    color: Int
  ) {
    placeFragment(fm, DashboardFragment())
    hightLightMenuItem(bottomNavigationView, R.id.navigation_dashboard, color)
  }

  fun goToFilmesFragment(
    fm: FragmentManager,
    bottomNavigationView: BottomNavigationView,
    color: Int
  ) {
    placeFragment(fm, FilmesFragment())
    hightLightMenuItem(bottomNavigationView, R.id.navigation_movie_presentation, color)
  }

  fun goToRegistoFragment(
    fm: FragmentManager,
    bottomNavigationView: BottomNavigationView,
    color: Int
  ) {
    placeFragment(fm, RegistoFilmesFragment())
    hightLightMenuItem(bottomNavigationView, R.id.navigation_movie_register, color)
  }

  fun goToFilmesDetailFragment(fm: FragmentManager, id: String) {
    placeFragment(fm, FilmesDetailFragment.newInstance(id))
  }

  @SuppressLint("ResourceAsColor")
  private fun hightLightMenuItem(bottomNavigationView: BottomNavigationView, id: Int, color: Int) {
    val item = bottomNavigationView.findViewById<View>(id)

    val idList = listOf(
      R.id.navigation_dashboard,
      R.id.navigation_movie_presentation,
      R.id.navigation_movie_register
    )

    idList.forEach {
      val item = bottomNavigationView.findViewById<View>(it)
      item.setBackgroundResource(0)
    }

    item.setBackgroundColor(color)
  }
}
