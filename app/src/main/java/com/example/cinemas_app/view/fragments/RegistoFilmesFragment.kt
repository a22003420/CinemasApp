package com.example.cinemas_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentRegistoFilmesBinding

class RegistoFilmesFragment : Fragment() {

    private lateinit var binding: FragmentRegistoFilmesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registo_filmes, container, false)
        binding = FragmentRegistoFilmesBinding.bind(view)
        return binding.root
    }



}
