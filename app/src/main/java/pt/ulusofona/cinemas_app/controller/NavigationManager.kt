package pt.ulusofona.cinemas_app.controller

import pt.ulusofona.cinemas_app.view.FilmesFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.cinemas_app.R
import pt.ulusofona.cinemas_app.view.FilmesDetailFragment
import pt.ulusofona.cinemas_app.view.fragments.DashboardFragment
import pt.ulusofona.cinemas_app.view.fragments.RegistoFilmesFragment

object NavigationManager {

  private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
    val transition = fm.beginTransaction()
    transition.replace(R.id.fragment_container, fragment)
    transition.addToBackStack(null)
    transition.commit()
  }

  fun goToDashboardFragment(fm: FragmentManager) {
    placeFragment(fm, DashboardFragment())
  }

  fun goToFilmesFragment(fm: FragmentManager) {
    placeFragment(fm, FilmesFragment())
  }

  fun goToRegistoFragment(fm: FragmentManager) {
    placeFragment(fm, RegistoFilmesFragment())
  }

  fun goToFilmesDetailFragment(fm: FragmentManager, id: String) {
    placeFragment(fm, FilmesDetailFragment.newInstance(id))
  }
}