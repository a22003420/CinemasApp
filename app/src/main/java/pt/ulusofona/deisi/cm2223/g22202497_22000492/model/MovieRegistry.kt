package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import android.net.Uri
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieRegistryDB
import java.io.Serializable
import java.time.Year
import java.util.*

data class MovieRegistry(
  var id: Long = 0,
  var movie: Movie = Movie(),
  var cinema: Cinema = Cinema(),
  var rate : Int = 0,
  var seen: String = "",
  var isChecked: Boolean = false,
  var observations: String = "",
  var clickCount: Int = 0,  // Count clicks filme
  var images: List<RegistryImage> = listOf()) : Serializable {

  fun toMovieRegistryDB(): MovieRegistryDB {
    return MovieRegistryDB(
      id = this.id,
      movieId = this.movie.id,
      rate = this.rate,
      seen = this.seen,
      isChecked = this.isChecked,
      cinemaId = this.cinema.id,
      observations = this.observations,
      clickCount = this.clickCount
    )
  }

  fun rateColor(): Float {
    return when (this.rate) {
      1,2 -> BitmapDescriptorFactory.HUE_RED
      3,4 -> BitmapDescriptorFactory.HUE_ORANGE
      5,6 -> BitmapDescriptorFactory.HUE_YELLOW
      7,8 -> BitmapDescriptorFactory.HUE_BLUE
      9,10 -> BitmapDescriptorFactory.HUE_GREEN
      else -> BitmapDescriptorFactory.HUE_RED
    }
  }
}
