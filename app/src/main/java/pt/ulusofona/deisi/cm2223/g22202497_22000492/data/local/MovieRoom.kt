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
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.LocalOps
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.RegistryImage


class MovieRoom(
  private val movieStorage: MovieDao,
  private val registryStorage: MovieRegistryDao,
  private val registryImageStorage: RegistryImageDao,
  private val cinemaStorage : CinemaDao,
  private val context: Context
): LocalOps() {
  override fun getMovies(onFinished: (Result<List<Movie>>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
      val movies = movieStorage.getAllMovies().map {
        var registry = movieStorage.getMovieFromRegistry(it.id)
        var movie = it.toMovie()

        if (registry !== null) {
          var cinema = cinemaStorage.getCinemaById(registry.cinemaId)
          movie.registry = registry.toMovieRegistry(
            cinema?.name ?: "",
            movie
          )
        }

        movie
      }
      onFinished(Result.success(movies))
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
      val cinema = cinemaStorage.getCinemaByName(registry.cinema)

      if (cinema === null) {
        onFinished(Result.failure(Exception(context.getString(R.string.cinema_not_found))))
      }
      else {
        val registryDB = registry.toMovieRegistryDB()
        registryDB.cinemaId = cinema.id

        registryStorage.insertRegistry(
          registry.toMovieRegistryDB()
        )

        onFinished(Result.success(registry))
      }
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
