package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object History {
  val registryList : MutableList<MovieRegistry> = mutableListOf()

  fun loadMovies(context: Context): List<Movie> {
    val jsonString = context.assets.open("movies.json").bufferedReader().use { it.readText() }
    return Gson().fromJson(jsonString, object : TypeToken<List<Movie>>() {}.type)
  }

  fun loadCinemas(context: Context): List<Cinema> {
    val jsonString = context.assets.open("cinemas.json").bufferedReader().use { it.readText() }
    return Gson().fromJson(jsonString, object : TypeToken<List<Cinema>>() {}.type)
  }

  fun getMovieById(paramMovieList: List<Movie>, uuid: String?): Movie? {
    return paramMovieList.find { it.id.toString() == uuid }
  }

  fun getMovieByName(paramMovieList: List<Movie>, name: String?): Movie? {
    return paramMovieList.find { it.name.equals(name, ignoreCase = true) }
  }

  fun getRegistryByMovieId(movieId: Int): MovieRegistry? {
    return null
    //return registryList.find { it.getMovieId() == movieId }
  }

  fun saveRegistry(registry: MovieRegistry) {
    /*val oldRegistry: MovieRegistry? = getRegistryByMovieId(registry.getMovieId());

    if (oldRegistry != null) {
      registryList.remove(oldRegistry)
    }

    registryList.add(registry)*/
  }

  fun top5ImdbMovies(movieList: List<Movie>): List<Movie> {
    return movieList.sortedByDescending { it.imdbRating }.take(5)
  }

  fun top5BestRatedMovies(movieList: List<Movie>): List<Movie> {
    return mutableListOf()
    /* val sortedMovies = registryList
      .sortedByDescending { it.getRate() }
      .map { getMovieById(movieList, it.getMovieId().toString()) }
      .take(5)

    return sortedMovies.filterNotNull() */
  }

  fun top5LastSeenMovies(movieList: List<Movie>): List<Movie> {
    return mutableListOf()
    /*val sortedMovies = registryList
      .sortedByDescending { it.getSeenDate() }
      .map { getMovieById(movieList, it.getMovieId().toString()) }
      .take(5)

    return sortedMovies.filterNotNull()*/
  }
}