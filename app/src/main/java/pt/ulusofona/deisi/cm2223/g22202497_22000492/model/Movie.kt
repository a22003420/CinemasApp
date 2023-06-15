package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import com.google.gson.annotations.SerializedName
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import java.io.Serializable
import java.util.*

data class Movie  (
  var id: String = "",
  var name: String = "",
  var year: String = "",
  var photo: String = "",
  var genre: String = "",
  var synopsis: String = "",
  var releaseDate: String = "",
  var imdbRating: String = "",
  var dvd: String = "",
  var imdbLink: String = ""
) : Serializable {
  fun getReleaseDate(): Date {
    val date = releaseDate.split("/")
    return Date(date[0].toInt(), date[1].toInt(), date[2].toInt())
  }

  fun toMovieDB(): MovieDB {
    return MovieDB(
      id = this.id,
      name = this.name,
      year = this.year,
      photo = this.photo,
      genre = this.genre,
      synopsis = this.synopsis,
      releaseDate = this.releaseDate,
      dvd= this.dvd,
      imdbRating = this.imdbRating,
      imdbLink = this.imdbLink
    )
  }

  fun isAction(): Boolean {
    return genre.contains("Action")
  }

}
