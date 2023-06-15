package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.FragmentFilmesDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Cinema
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry
import pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters.ImagesDetailsAdapter

class FilmesDetailFragment : Fragment() {

  private lateinit var binding: FragmentFilmesDetailBinding
  private var operationUuid: String? = null
  private var clickSource: String = ""
  private var movie: Movie? = null
  private var registry: MovieRegistry? = null
  private var cinema: Cinema? = null
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

    operationUuid?.let {
      MovieRepository.getInstance().getMovieRegistry(it.toLong()) { result ->
        result.getOrNull().let {
          registry = it
          movie = registry?.movie
          cinema = registry?.cinema

          CoroutineScope(Dispatchers.Main).launch {
            placeData()
          }
        }
      }
    }

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
      cinemaMorada.text =cinema?.address
      code.text=cinema?.postcode
      cinemaCity.text=cinema?.county
      detailSource.text = clickSource






      if (registry == null) {
        registryLayout.visibility = View.GONE
        registryShare.visibility = View.GONE
      } else {
        cinemaName.text = registry?.cinema?.name
        registryRate.text = registry?.rate.toString()
        registrySeenDate.text = registry?.seen
        registrySee.text = if (registry?.isChecked == true) {
          "Sim"
        } else {
          "Não"
        }


        if (registry?.observations.toString().isBlank()) {
          registryObservationsLabel.visibility = View.GONE
          registryObservations.visibility = View.GONE
        } else {
          registryObservations.text = registry?.observations
        }

        if (registry?.images != null && registry?.images!!.isEmpty()) {
          registryPhotosLabel.visibility = View.GONE
          registryPhotosList.visibility = View.GONE
        } else {
          binding.registryPhotosList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
          binding.registryPhotosList.adapter = ImagesDetailsAdapter(registry?.images!!.map { it.toUri() })
        }
        registryShare.visibility = View.VISIBLE
      }

      Glide.with(requireContext())
        .load(movie?.photo)
        .into(movieImage)

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
    val shareText = if(registry?.observations == null || registry?.observations!!.isBlank()) {
      getString(
        R.string.share_body,
        movie?.name,
        registry?.seen,
        registry?.cinema,
        registry?.rate.toString(),
        movie?.imdbLink
      )
    } else {
      getString(
        R.string.share_body_with_observations,
        movie?.name,
        registry?.seen,
        registry?.cinema,
        registry?.rate.toString(),
        movie?.imdbLink,
        registry?.observations)
    }

    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_label)))
  }








}