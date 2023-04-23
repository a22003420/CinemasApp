package pt.ulusofona.cinemas_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemas_app.R
import pt.ulusofona.cinemas_app.controller.NavigationManager
import com.example.cinemas_app.databinding.FragmentFilmesBinding
import pt.ulusofona.cinemas_app.model.History
import pt.ulusofona.cinemas_app.model.Movie
import pt.ulusofona.cinemas_app.view.adapters.MovieAdapter


class FilmesFragment : Fragment() {
  private lateinit var adapter : MovieAdapter
  private lateinit var binding: FragmentFilmesBinding
  private lateinit var myMovieList: List<Movie>

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    myMovieList = History.loadMovies(requireContext())
    adapter = MovieAdapter(::onOperationClick, myMovieList)
    val view = inflater.inflate(R.layout.fragment_filmes, container, false)
    binding = FragmentFilmesBinding.bind(view)
    binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
    binding.rvHistory.adapter = adapter
    return binding.root
  }

  private fun onOperationClick(uuid: String) {
    NavigationManager.goToFilmesDetailFragment(parentFragmentManager, uuid)
  }
}
