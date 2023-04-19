package com.example.cinemas_app.view.fragments

import History
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.R
import com.example.cinemas_app.controller.NavigationManager
import com.example.cinemas_app.databinding.FragmentDashboardBinding
import com.example.cinemas_app.view.FilmesAdapter

class DashboardFragment : Fragment() {

  private val adapterTopMovies = FilmesAdapter(::onOperationClick, History.top5RatedMovies())
  private val adapterRecentMovies = FilmesAdapter(::onOperationClick, History.top5RecentMovies())
  private val adapterLastSeenMovies = FilmesAdapter(::onOperationClick, History.top5LastSeenMovies())
  private lateinit var binding: FragmentDashboardBinding

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
    binding = FragmentDashboardBinding.bind(view)
    return binding.root
  }

  override fun onStart() {
    super.onStart()

    initAdapter(binding.bestMoviesRecyclerView, adapterTopMovies)
    initAdapter(binding.recentMoviesRecyclerView, adapterRecentMovies)
    initAdapter(binding.lastSeenMoviesRecyclerView, adapterLastSeenMovies)
  }

  private fun initAdapter(view: RecyclerView, adapter: FilmesAdapter) {
    view.layoutManager = LinearLayoutManager(
      requireContext(),
      LinearLayoutManager.HORIZONTAL,
      false)
    view.adapter = adapter
  }

  private fun onOperationClick(uuid: String) {
    NavigationManager.goToFilmesDetailFragment(parentFragmentManager, uuid)
  }
}
