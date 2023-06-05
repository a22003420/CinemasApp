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
    fun getReleaseDate(): Date {
     val date = releaseDate.split("/")
     return Date(date[0].toInt(), date[1].toInt(), date[2].toInt())
    }
}
