package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentDashboardBinding
import com.example.cinemas_app.databinding.FragmentFilmesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters.MovieAdapter
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters.TabAdapter


class FilmesFragment : Fragment() {
  private lateinit var binding: FragmentFilmesBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_filmes, container, false)
    binding = FragmentFilmesBinding.bind(view)
    val tabLayout: TabLayout = binding.tabLayout
    val viewPager: ViewPager2 = binding.viewPager

    val tabAdapter = TabAdapter(childFragmentManager, lifecycle, parentFragmentManager)
    viewPager.adapter = tabAdapter
    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
      tab.text = when (position) {
        0 -> getString(R.string.movies_tab_list)
        1 -> getString(R.string.movies_tab_map)
        else -> throw IllegalArgumentException("Invalid tab position")
      }
    }.attach()

    return view
  }
}
