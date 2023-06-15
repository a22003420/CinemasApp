package pt.ulusofona.deisi.cm2223.g22202497_22000492.controller

import android.view.View
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments.FilmesFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.cinemas_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments.FilmesDetailFragment
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments.DashboardFragment
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments.RegistoFilmesFragment
import java.security.AccessController.getContext

// Objeto responsável pela navegação entre fragmentos
object NavigationManager {

  // Função privada para colocar o fragmento no container
  private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
    val transition = fm.beginTransaction()
    transition.replace(R.id.fragment_container, fragment)
    transition.addToBackStack(null)
    transition.commit()
  }

  // Navega para o fragmento do Dashboard
  fun goToDashboardFragment(
    fm: FragmentManager,
    bottomNavigationView: BottomNavigationView,
    color: Int
  ) {
    placeFragment(fm, DashboardFragment())
    hightLightMenuItem(bottomNavigationView, R.id.navigation_dashboard, color)
  }

  // Navega para o fragmento de Filmes
  fun goToFilmesFragment(
    fm: FragmentManager,
    bottomNavigationView: BottomNavigationView,
    color: Int
  ) {
    placeFragment(fm, FilmesFragment())
    hightLightMenuItem(bottomNavigationView, R.id.navigation_movie_presentation, color)
  }

  // Navega para o fragmento de Registo de Filmes
  fun goToRegistoFragment(
    fm: FragmentManager,
    bottomNavigationView: BottomNavigationView,
    color: Int
  ) {
    placeFragment(fm, RegistoFilmesFragment())
    hightLightMenuItem(bottomNavigationView, R.id.navigation_movie_register, color)
  }

  // Navega para o fragmento de Detalhes de Filmes
  fun goToFilmesDetailFragment(fm: FragmentManager, id: String, source: String) {
    placeFragment(fm, FilmesDetailFragment.newInstance(id, source))
  }

  // Destaca o item de menu selecionado na BottomNavigationView
  private fun hightLightMenuItem(bottomNavigationView: BottomNavigationView, id: Int, color: Int) {
    val item = bottomNavigationView.findViewById<View>(id)

    val idList = listOf(
      R.id.navigation_dashboard,
      R.id.navigation_movie_presentation,
      R.id.navigation_movie_register
    )

    // Remove o destaque de todos os itens de menu
    idList.forEach {
      val item = bottomNavigationView.findViewById<View>(it)
      item.setBackgroundResource(0)
    }

    // Define o destaque para o item selecionado
    item.setBackgroundColor(color)
  }
}
