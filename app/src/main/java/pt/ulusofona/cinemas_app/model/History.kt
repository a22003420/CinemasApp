package pt.ulusofona.cinemas_app.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object History {
  val movieList : MutableList<Filme> = mutableListOf()
  val registryList : MutableList<MovieRegistry> = mutableListOf()

  fun loadMovies(context: Context): List<Movie> {
    val jsonString = context.assets.open("movies.json").bufferedReader().use { it.readText() }
    return Gson().fromJson(jsonString, object : TypeToken<List<Movie>>() {}.type)
  }

  fun getMovieById(paramMovieList: List<Movie>, uuid: String?): Movie? {
    return paramMovieList.find { it.getId().toString() == uuid }
  }

  fun getMovieByName(paramMovieList: List<Movie>, name: String?): Movie? {
    return paramMovieList.find { it.getName() == name }
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

  fun getOperationById(uuid: String): Filme? {
    return null
  }

  fun top5RatedMovies(): List<Filme> {
    return listOf()
  }

  fun top5RecentMovies(): List<Filme> {
    return listOf()
  }

  fun top5LastSeenMovies(): List<Filme> {
    return listOf()
  }
}