package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemas_app.R
import pt.ulusofona.deisi.cm2223.g22202497_22000492.controller.NavigationManager
import com.example.cinemas_app.databinding.FragmentMovieListBinding
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters.MovieAdapter


class MovieListFragment(private val topFragmentManager: FragmentManager) : Fragment() {
  private lateinit var adapter : MovieAdapter
  private lateinit var binding: FragmentMovieListBinding
  private lateinit var myMovieList: List<MovieRegistry>

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    myMovieList = emptyList()
    adapter = MovieAdapter(::onOperationClick, myMovieList)

    MovieRepository.getInstance().getMoviesList { result ->
      result.getOrNull().let {
        if (it != null) {
          myMovieList = it
          adapter.setMovieList(myMovieList)
        }
      }
    }

    val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
    binding = FragmentMovieListBinding.bind(view)
    binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
    binding.rvHistory.adapter = adapter
    return binding.root
  }

  private fun onOperationClick(uuid: String) {
    NavigationManager.goToFilmesDetailFragment(topFragmentManager, uuid)
  }
}
