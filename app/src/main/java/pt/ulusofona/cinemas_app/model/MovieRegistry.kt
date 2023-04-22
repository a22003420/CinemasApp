package pt.ulusofona.cinemas_app.model

import android.net.Uri
import java.util.*

data class MovieRegistry(
    private val movieId: Int = 0,
    private val cinema: String = "",
    private val rate : Int = 0,
    private val seen: String = "",
    private val observations: String = "",
    private var images: List<Uri> = listOf()) {

    fun getMovieId(): Int {
        return movieId
    }

    fun getCinema(): String {
        return cinema
    }

    fun getRate(): Int {
        return rate
    }

    fun getObservations(): String {
        return observations
    }

    fun getSeen(): String {
        return seen
    }

    fun getImages(): List<Uri> {
        return images
    }

    fun getSeenDate(): Date {
        val date = getSeen().split("/")
        return Date(date[0].toInt(), date[1].toInt(), date[2].toInt())
    }

    fun setImages(images: List<Uri>) {
        this.images = images.toList()
    }
}
