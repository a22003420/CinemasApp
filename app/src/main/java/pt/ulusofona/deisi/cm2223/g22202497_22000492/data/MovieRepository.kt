package pt.ulusofona.deisi.cm2223.g22202497_22000492.data

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.cinemas_app.R
import pt.ulusofona.deisi.cm2223.g22202497_22000492.ConnectivityUtil
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.AppDatabase
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.RegistryImageDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.*

class MovieRepository (
  private val local: LocalOps,
  private val remote: RemoteOps,
  private val context: Context
) {

  private val appDatabase = AppDatabase.getInstance(context)

  fun getMovie(
    id: String,
    onFinished: (Result<Movie>) -> Unit) {
    if (ConnectivityUtil.isOnline(context)) {
      remote.getMovie(id) { result ->
        if (result.isSuccess) {
          result.getOrNull()?.let { movie ->

            local.updateMovie(movie) {
              local.getMovieById(movie.id) { result ->
                onFinished(result)
              }
            }
          }
        } else {
          onFinished(result)
        }
      }

    } else {
      local.getMovieById(id) { result ->
        if (result.isSuccess) {
          result.getOrNull()?.let { movie ->
            onFinished(Result.success(movie))
          }
        } else {
          onFinished(result)
        }
      }
    }
  }

  fun getMovieRegistry(
    id: Long,
    onFinished: (Result<MovieRegistry>) -> Unit) {
    local.getRegistryById(id) { result ->
      if (result.isSuccess) {
        result.getOrNull()?.let { registry ->
          if (ConnectivityUtil.isOnline(context)) {
            remote.getMovie(registry.movie.id) { result ->
              if (result.isSuccess) {
                result.getOrNull()?.let { movie ->
                  local.updateMovie(movie) {
                    local.getMovieById(movie.id) { result ->
                      result.getOrNull()?.let { movie ->
                        registry.movie = movie
                        onFinished(Result.success(registry))
                      }
                    }
                  }
                }
              }
            }
          }
          else {
            onFinished(Result.success(registry))
          }
        }
      } else {
        onFinished(result)
      }
    }
  }

  fun getMoviesList(
    onFinished: (Result<List<MovieRegistry>>) -> Unit) {
    local.getMovieList() { result ->
      if (result.isSuccess) {
        result.getOrNull()?.let { registries ->
          onFinished(Result.success(registries))
        }
      } else {
        onFinished(result)
      }
    }
  }

  fun getCinemasList(
    onFinished: (Result<List<Cinema>>) -> Unit) {
    local.getCinemaList() { result ->
      if (result.isSuccess) {
        result.getOrNull()?.let { cinemas ->
          onFinished(Result.success(cinemas))
        }
      } else {
        onFinished(result)
      }
    }
  }

  fun getRegistryByMovieName(
    id: String,
    onFinished: (Result<MovieRegistry>) -> Unit) {
    local.getRegistryByMovieName(id) { result ->
      if (result.isSuccess) {
        result.getOrNull()?.let { registry ->
          onFinished(Result.success(registry))
        }
      } else {
        onFinished(result)
      }
    }
  }

  fun deleteRegistryImage(
    registryImage: RegistryImage,
    onFinished: () -> Unit) {
    local.deleteRegistryImage(
      registryImage
    ) {
      onFinished()
    }
  }

  fun saveRegistry(
    movieRegistry: MovieRegistry,
    onFinished: (Result<MovieRegistry>) -> Unit) {
    if (!ConnectivityUtil.isOnline(context)) {
      onFinished(
        Result.failure(
          Exception(context.getString(R.string.no_internet_connection))
        )
      )
      return
    }

    remote.searchMovie(movieRegistry.movie.name, movieRegistry.movie.year) { result ->
      if (result.isSuccess) {
        result.getOrNull()?.let { movie ->
          local.insertMovie(movie) { result ->
            if (result.isSuccess) {
              movieRegistry.movie = movie
              local.insertCinema(movieRegistry.cinema) { result ->
                if (result.isSuccess) {
                  local.insertRegistry(movieRegistry) { result ->
                    result.getOrNull()?.let { registry ->
                      if (result.isSuccess) {
                        local.insertRegistryImages(registry, movieRegistry.images) { result ->
                          if (result.isSuccess) {
                            result.getOrNull()?.let { images ->
                              onFinished(Result.success(movieRegistry))
                            }
                          }
                        }
                      } else {
                        onFinished(result)
                      }
                    }
                  }
                }
              }
            }
          }
        }
      } else {
        onFinished(Result.failure(Exception(context.getString(R.string.movie_not_found))))
      }
    }
  }

  companion object {

    @SuppressLint("StaticFieldLeak")
    private var instance: MovieRepository? = null

    // Temos de executar o init antes do getInstance
    fun init(local: LocalOps, remote: RemoteOps, context: Context) {
      synchronized(this) {
        if (instance == null) {
          instance = MovieRepository(local, remote, context)
        }
      }
    }

    fun getInstance(): MovieRepository {
      if (instance == null) {
        // Primeiro temos de invocar o init, se não lança esta Exception
        throw IllegalStateException("singleton not initialized")
      }
      return instance as MovieRepository
    }

  }
}
