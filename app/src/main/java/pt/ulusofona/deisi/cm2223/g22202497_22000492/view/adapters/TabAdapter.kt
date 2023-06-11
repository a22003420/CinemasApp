package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments.MapFragment
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments.MovieListFragment

class TabAdapter(
  fragmentManager: FragmentManager,
  lifecycle:Lifecycle,
  private val topFragmentManager: FragmentManager) : FragmentStateAdapter(fragmentManager, lifecycle) {
  override fun getItemCount(): Int = 2

  override fun createFragment(position: Int): Fragment {
    return when (position) {
      0 -> MovieListFragment(topFragmentManager)
      1 -> MapFragment()
      else -> throw IllegalArgumentException("Invalid tab position")
    }
  }
}
