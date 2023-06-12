package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.JSONObject


object History {

  fun loadCinemas(context: Context): List<Cinema> {
    val jsonString = context.assets.open("cinemas.json").bufferedReader().use { it.readText() }
    val jsonObject = JSONObject(jsonString)
    val cinemasJsonArray = jsonObject.getJSONArray("cinemas")

    val cinemasList = mutableListOf<Cinema>()
    for (i in 0 until cinemasJsonArray.length()) {
      val cinemaJsonObject = cinemasJsonArray.getJSONObject(i)
      val cinema = Cinema(
        id = cinemaJsonObject.getLong("cinema_id"),
        name = cinemaJsonObject.getString("cinema_name"),
        provider = cinemaJsonObject.getString("cinema_provider"),
        latitude = cinemaJsonObject.getDouble("latitude"),
        longitude = cinemaJsonObject.getDouble("longitude"),
        address = cinemaJsonObject.getString("address"),
        county = cinemaJsonObject.getString("county")
      )
      cinemasList.add(cinema)
    }

    return cinemasList
  }

  fun getCinemaByName(paramCinemaList: List<Cinema>, name: String?): Cinema? {
    return paramCinemaList.find { it.name.equals(name, ignoreCase = true) }
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