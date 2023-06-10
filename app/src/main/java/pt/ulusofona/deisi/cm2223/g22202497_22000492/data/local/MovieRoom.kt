package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local

import android.content.Context
import android.util.Log
import com.example.cinemas_app.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.CinemaDao
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.MovieDao
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.MovieRegistryDao
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.RegistryImageDao
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieRegistryDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.RegistryImageDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.*


class MovieRoom(
  private val movieStorage: MovieDao,
  private val registryStorage: MovieRegistryDao,
  private val registryImageStorage: RegistryImageDao,
  private val cinemaStorage : CinemaDao,
  private val context: Context
): LocalOps() {
  override fun getMovieList(onFinished: (Result<List<MovieRegistry>>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      val registries = registryStorage.getAllRegistries().map{
        var movie = movieStorage.getMovieById(it.movieId)
        var cinema = cinemaStorage.getCinemaById(it.cinemaId)
        var images = registryImageStorage.getImagesForRegistry(it.id)

        var registry = it.toMovieRegistry(
          cinema.toCinema(),
          movie?.toMovie() ?: Movie(),
          images
        )
        registry
      }

      onFinished(Result.success(registries))
    }
  }

  override fun updateMovie(movie: Movie, onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      movieStorage.updateMovie(
        movie.toMovieDB()
      )
      onFinished()
    }
  }

  override fun getMovieById(id: String, onFinished: (Result<Movie>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      val movie = movieStorage.getMovieById(id)

      if (movie === null) {
        onFinished(Result.failure(Exception(context.getString(R.string.movie_not_found))))
      }
      else {
        onFinished(Result.success(
          movie.toMovie()
        ))
      }
    }
  }


  override fun insertMovies(movies: List<Movie>, onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      movies.map {
        it.toMovieDB()
      }
        .forEach {
          movieStorage.insertMovie(it)
        }

      onFinished()
    }
  }

  override fun insertMovie(movie: Movie, onFinished: (Result<Movie>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      movieStorage.insertMovie(
        movie.toMovieDB()
      )
      onFinished(Result.success(movie))
    }
  }

  override fun insertRegistry(registry: MovieRegistry, onFinished: (Result<MovieRegistry>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {

      registry.id = registryStorage.insertRegistry(
        registry.toMovieRegistryDB()
      )

      onFinished(Result.success(registry))
    }
  }

  override fun insertCinema(cinema: Cinema, onFinished: (Result<Cinema>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      cinemaStorage.insertCinema(
        cinema.toCinemaDB()
      )
      onFinished(Result.success(cinema))
    }
  }

  override fun insertRegistryImage(registryImage: RegistryImage, onFinished: (Result<RegistryImage>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      registryImageStorage.insertRegistryImage(
        registryImage.toRegistryImageDB()
      )
      onFinished(Result.success(registryImage))
    }
  }

  override fun insertRegistryImages(movieRegistry: MovieRegistry, registryImages: List<RegistryImage>, onFinished: (Result<List<RegistryImage>>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      registryImageStorage.insertRegistryImages(
        registryImages.map {
          it.movieRegistryId = movieRegistry.id
          it.toRegistryImageDB()
        }
      )

      onFinished(Result.success(registryImages))
    }
  }

  override fun clearAllMovies(onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      movieStorage.deleteAllMovies()
      onFinished()
    }
  }

  override fun deleteRegistryImage(registryImage: RegistryImage, onFinished: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      registryImageStorage.deleteRegistryImage(
        registryImage.toRegistryImageDB()
      )
      onFinished()
    }
  }
}
