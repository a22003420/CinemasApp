package com.example.cinemas_app.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentFilmesDetailBinding
import com.example.cinemas_app.model.Filme
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_OPERATION_UUID = "ARG_OPERATION_UUID"

class FilmesDetailFragment : Fragment() {

  private lateinit var binding: FragmentFilmesDetailBinding
  private var operationUuid: String? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      operationUuid = it.getString(ARG_OPERATION_UUID)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_filmes_detail, container, false)
    binding = FragmentFilmesDetailBinding.bind(view)
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    operationUuid?.let { uuid ->
      val operation = History.getOperationById(uuid)
      operation?.let { placeData(it) }
    }
    binding.movieImdbLink.setOnClickListener { // LINK IMDB associado ao botão
      openIMDBLink(it)
    }
  }

  private fun placeData(ui: Filme) {
    binding.apply {
      movieTitle.text = ui.nome
      movieGenero.text = "Terror"
      movieSynopsis.text = "Um filme de terror" // synopse
      movieReleaseDate.text = "2022" // ano
      // Popula do ecrã de Registo de Filmes aqui nos details
      cinemaName.text=ui.cinema
      avaResult.text=ui.classificacao.toString()
      val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
      val formattedDate = ui.ano?.let { dateFormat.format(it) }
      dateViewResult.text = formattedDate
      // imagem
      observacoesResult.text=ui.observacoes
    }
  }

  companion object {

    @JvmStatic
    fun newInstance(uuid: String) =
      FilmesDetailFragment().apply {
        arguments = Bundle().apply {
          putString(ARG_OPERATION_UUID, uuid)
        }
      }
  }

  fun openIMDBLink(view: View) { // LINK IMDB
    val imdbLink = "https://www.imdb.com/"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imdbLink))
    startActivity(intent)
  }
}
