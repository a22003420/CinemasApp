package pt.ulusofona.cinemas_app.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentFilmesDetailBinding
import pt.ulusofona.cinemas_app.model.History
import pt.ulusofona.cinemas_app.model.Movie

private const val ARG_OPERATION_UUID = "ARG_OPERATION_UUID"

class FilmesDetailFragment : Fragment() {

  private lateinit var binding: FragmentFilmesDetailBinding
  private var operationUuid: String? = null
  private var movie: Movie? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      operationUuid = it.getString(ARG_OPERATION_UUID)
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_filmes_detail, container, false)
    binding = FragmentFilmesDetailBinding.bind(view)
    val movieList = History.loadMovies(requireContext())
    movie = History.getMovieById(movieList, operationUuid)
    return binding.root
  }

  override fun onStart() {
    super.onStart()
    placeData()
    binding.movieImdbLink.setOnClickListener {
      openIMDBLink()
    }
  }

  private fun placeData() {
    binding.apply {
      movieTitle.text = movie?.getName()
      movieGenero.text = movie?.getGenre()
      movieSynopsis.text = movie?.getSynopsis()
      movieReleaseDate.text = movie?.getReleaseDateString()
      movieImdbRating.text = "IMDB: ${movie?.getImdbRating().toString()}"

      val resourceId = context?.resources?.getIdentifier(movie?.getPhoto(), "drawable",  context?.packageName)
      if (resourceId != null) {
        movieImage.setImageResource(resourceId)
      }

      /*
      cinemaName.text = ui.cinema
      avaResult.text = ui.classificacao.toString()
      val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
      val formattedDate = ui.visto?.let { dateFormat.format(it) }
      dateViewResult.text = formattedDate
      // imagem
      observacoesResult.text=ui.observacoes
      */
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

  fun openIMDBLink() { // LINK IMDB
    val imdbLink = movie?.getImdbLink()
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imdbLink))
    startActivity(intent)
  }
}
