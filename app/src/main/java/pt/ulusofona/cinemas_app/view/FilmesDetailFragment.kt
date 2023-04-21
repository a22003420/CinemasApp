package pt.ulusofona.cinemas_app.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentFilmesDetailBinding
import pt.ulusofona.cinemas_app.model.History
import pt.ulusofona.cinemas_app.model.Movie
import pt.ulusofona.cinemas_app.model.MovieRegistry
import pt.ulusofona.cinemas_app.view.adapters.ImagesDetailsAdapter

private const val ARG_OPERATION_UUID = "ARG_OPERATION_UUID"

class FilmesDetailFragment : Fragment() {

  private lateinit var binding: FragmentFilmesDetailBinding
  private var operationUuid: String? = null
  private var movie: Movie? = null
  private var registry: MovieRegistry? = null

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
    registry = History.getRegistryByMovieId(movie!!.getId())

    placeData()
    binding.movieImdbLink.setOnClickListener {
      openIMDBLink()
    }

    return binding.root
  }

  override fun onStart() {
    super.onStart()

  }

  private fun placeData() {
    binding.apply {
      movieTitle.text = movie?.getName()
      movieGenero.text = movie?.getGenre()
      movieSynopsis.text = movie?.getSynopsis()
      movieReleaseDate.text = movie?.getReleaseDateString()
      movieImdbRating.text = getString(R.string.movie_imdb_rating,movie?.getImdbRating().toString())

      if (registry == null) {
        registryLayout.visibility = View.GONE
      } else {
        cinemaName.text = registry?.getCinema()
        registryRate.text = registry?.getRate().toString()
        registrySeenDate.text = registry?.getSeen()

        if (registry?.getObservations().toString().isBlank()) {
          registryObservationsLabel.visibility = View.GONE
          registryObservations.visibility = View.GONE
        } else {
          registryObservations.text = registry?.getObservations()
        }

        if (registry?.getImages() != null && registry?.getImages()!!.isEmpty()) {
          registryPhotosLabel.visibility = View.GONE
          registryPhotosList.visibility = View.GONE
        } else {
          binding.registryPhotosList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
          binding.registryPhotosList.adapter = ImagesDetailsAdapter(registry?.getImages()!!)
        }
      }

      val resourceId = context?.resources?.getIdentifier(movie?.getPhoto(), "drawable",  context?.packageName)
      if (resourceId != null) {
        movieImage.setImageResource(resourceId)
      }
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