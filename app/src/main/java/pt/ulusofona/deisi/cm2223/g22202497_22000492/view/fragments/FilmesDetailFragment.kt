package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentFilmesDetailBinding
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.History
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters.ImagesDetailsAdapter

class FilmesDetailFragment : Fragment() {

  private lateinit var binding: FragmentFilmesDetailBinding
  private var operationUuid: String? = null
  private var movie: Movie? = null
  private var registry: MovieRegistry? = null
  private val ARG_OPERATION_UUID = "ARG_OPERATION_UUID"

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
    registry = History.getRegistryByMovieId(movie!!.id.toInt())

    placeData()
    binding.movieImdbLink.setOnClickListener {
      openIMDBLink()
    }

    val shareButton = view.findViewById<Button>(R.id.registryShare)
    shareButton.setOnClickListener {
      registryShare(it)
    }


    return binding.root
  }

  override fun onStart() {
    super.onStart()

  }

  private fun placeData() {
    binding.apply {
      movieTitle.text = movie?.name
      movieGenero.text = movie?.genre
      movieSynopsis.text = movie?.synopsis
      movieReleaseDate.text = movie?.releaseDate.toString()
      movieImdbRating.text = getString(R.string.movie_imdb_rating,movie?.imdbRating.toString())

      if (registry == null) {
        registryLayout.visibility = View.GONE
        registryShare.visibility = View.GONE
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
          binding.registryPhotosList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
          binding.registryPhotosList.adapter = ImagesDetailsAdapter(registry?.getImages()!!)
        }
        registryShare.visibility = View.VISIBLE
      }

      val resourceId = context?.resources?.getIdentifier(movie?.photo, "drawable",  context?.packageName)
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

  fun openIMDBLink() {
    val imdbLink = movie?.imdbLink
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imdbLink))
    startActivity(intent)
  }

  private fun registryShare(view: View) {
    val shareText = if(registry?.getObservations() == null || registry?.getObservations()!!.isBlank()) {
      getString(
        R.string.share_body,
        movie?.name,
        registry?.getSeen(),
        registry?.getCinema(),
        registry?.getRate().toString(),
        movie?.imdbLink
      )
    } else {
      getString(
        R.string.share_body_with_observations,
        movie?.name,
        registry?.getSeen(),
        registry?.getCinema(),
        registry?.getRate().toString(),
        movie?.imdbLink,
        registry?.getObservations())
    }

    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_label)))
  }
}