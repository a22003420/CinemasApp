package pt.ulusofona.cinemas_app.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object History {
  val movieList : MutableList<Filme> = mutableListOf()

  fun loadMovies(context: Context): List<Movie> {
    val jsonString = context.assets.open("movies.json").bufferedReader().use { it.readText() }
    return Gson().fromJson(jsonString, object : TypeToken<List<Movie>>() {}.type)
  }

  fun getMovieById(paramMovieList: List<Movie>, uuid: String?): Movie? {
    return paramMovieList.find { it.getId().toString() == uuid }
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