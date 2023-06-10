package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import android.net.Uri
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieRegistryDB
import java.io.Serializable
import java.time.Year
import java.util.*

data class MovieRegistry(
  var id: Long = 0,
  var movieId: String = "",
  var movieName: String = "",
  var movieYear: Int = 0,
  var cinema: Cinema = Cinema(),
  var rate : Int = 0,
  var seen: String = "",
  var observations: String = "",
  var images: List<RegistryImage> = listOf()) : Serializable {

  fun getSeenDate(): Date {
    val date = seen.split("/")
    return Date(date[0].toInt(), date[1].toInt(), date[2].toInt())
  }

  fun toMovieRegistryDB(): MovieRegistryDB {
    return MovieRegistryDB(
      id = this.id,
      movieId = this.movieId,
      rate = this.rate,
      seen = this.seen,
      cinemaId = this.cinema.id,
      observations = this.observations
    )
  }
}
