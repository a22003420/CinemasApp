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
import com.example.cinemas_app.view.FilmesAdapter
import com.example.cinemas_app.view.History

class FilmesFragment : Fragment() {

    private val adapter = FilmesAdapter(::onOperationClick, History.historyItems)
    private lateinit var binding: FragmentFilmesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filmes, container, false)
        binding = FragmentFilmesBinding.bind(view)
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvHistory.adapter = adapter
    }

    private fun onOperationClick(uuid: String) {
        NavigationManager.goToFilmesDetailFragment(parentFragmentManager, uuid)
    }
}
