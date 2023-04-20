package com.example.cinemas_app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemas_app.R
import com.example.cinemas_app.controller.NavigationManager
import com.example.cinemas_app.databinding.FragmentFilmesBinding
import com.example.cinemas_app.model.History
import com.example.cinemas_app.model.Movie
import com.example.cinemas_app.view.adapters.FilmesAdapter
import com.example.cinemas_app.view.adapters.MovieAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class FilmesFragment : Fragment() {

  // private val adapter = FilmesAdapter(::onOperationClick, com.example.cinemas_app.model.History.movieList)
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
