package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Movie(
    @SerializedName("imdbID")
    val id: String,

    @SerializedName("Title")
    val name: String,

    //@SerializedName("Poster")
    val photo: String,

    @SerializedName("Genre")
    val genre: String,

    @SerializedName("Plot")
    val synopsis: String,

    @SerializedName("Released")
    val releaseDate: String,

    @SerializedName("imdbRating")
    val imdbRating: String,

    @SerializedName("DVD")
    val imdbLink: String
): Serializable


{
  // fun getId(): Int {
    // return id
    // }

    // fun getName(): String {
    // return name
    // }

    // fun getGenre(): String {
    //   return genre
    // }

    //  fun getSynopsis(): String {
    //   return synopsis
    //  }

    // fun getImdbLink(): String {
    //   return imdbLink
    //  }

    // fun getPhoto(): String {
    //  return photo
    //  }

    // fun getImdbRating(): Double {
    //   return imdbRating
    // }

    // fun getReleaseDateString(): String {
    //   return releaseDate
    //  }

    // fun getReleaseDate(): Date {
    //  val date = getReleaseDateString().split("/")
    //  return Date(date[0].toInt(), date[1].toInt(), date[2].toInt())
    // }
}
