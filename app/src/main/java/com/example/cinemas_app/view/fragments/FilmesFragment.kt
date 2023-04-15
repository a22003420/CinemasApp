package com.example.cinemas_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentFilmesBinding

class FilmesFragment : Fragment() {

    private lateinit var binding: FragmentFilmesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filmes, container, false)
        binding = FragmentFilmesBinding.bind(view)
        return binding.root
    }



}
