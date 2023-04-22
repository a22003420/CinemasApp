package pt.ulusofona.cinemas_app.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object History {
  val registryList : MutableList<MovieRegistry> = mutableListOf()

  fun loadMovies(context: Context): List<Movie> {
    val jsonString = context.assets.open("movies.json").bufferedReader().use { it.readText() }
    return Gson().fromJson(jsonString, object : TypeToken<List<Movie>>() {}.type)
  }

  fun getMovieById(paramMovieList: List<Movie>, uuid: String?): Movie? {
    return paramMovieList.find { it.getId().toString() == uuid }
  }

  fun getMovieByName(paramMovieList: List<Movie>, name: String?): Movie? {
    return paramMovieList.find { it.getName().toLowerCase() == name?.toLowerCase() }
  }

  fun getRegistryByMovieId(movieId: Int): MovieRegistry? {
    return registryList.find { it.getMovieId() == movieId }
  }

  fun saveRegistry(registry: MovieRegistry) {
    val oldRegistry: MovieRegistry? = getRegistryByMovieId(registry.getMovieId());

    if (oldRegistry != null) {
      registryList.remove(oldRegistry)
    }

    registryList.add(registry)
  }

  fun top5ImdbMovies(movieList: List<Movie>): List<Movie> {
    return movieList.sortedByDescending { it.getImdbRating() }.take(5)
  }

  fun top5BestRatedMovies(movieList: List<Movie>): List<Movie> {
    val sortedMovies = registryList
      .sortedByDescending { it.getRate() }
      .map { getMovieById(movieList, it.getMovieId().toString()) }
      .take(5)

    return sortedMovies.filterNotNull()
  }

  fun top5LastSeenMovies(movieList: List<Movie>): List<Movie> {
    val sortedMovies = registryList
      .sortedByDescending { it.getSeenDate() }
      .map { getMovieById(movieList, it.getMovieId().toString()) }
      .take(5)

    return sortedMovies.filterNotNull()
  }
}