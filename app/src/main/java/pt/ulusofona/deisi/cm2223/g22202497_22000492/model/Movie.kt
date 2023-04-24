package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Movie(
    private val id: Int,
    private val name: String,
    private val photo: String,
    private val genre: String,
    private val synopsis: String,
    @SerializedName("release_date")
    private val releaseDate: String,
    @SerializedName("imdb_rating")
    private val imdbRating: Double,
    @SerializedName("imdb_link")
    private val imdbLink: String,
) {
  fun getId(): Int {
    return id
  }

  fun getName(): String {
    return name
  }

  fun getGenre(): String {
    return genre
  }

  fun getSynopsis(): String {
    return synopsis
  }

  fun getImdbLink(): String {
    return imdbLink
  }

  fun getPhoto(): String {
    return photo
  }

  fun getImdbRating(): Double {
    return imdbRating
  }

  fun getReleaseDateString(): String {
    return releaseDate
  }

  fun getReleaseDate(): Date {
    val date = getReleaseDateString().split("/")
    return Date(date[0].toInt(), date[1].toInt(), date[2].toInt())
  }
}
