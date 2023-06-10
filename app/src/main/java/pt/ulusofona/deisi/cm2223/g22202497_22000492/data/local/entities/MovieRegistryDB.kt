package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities

import android.net.Uri
import androidx.room.*
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Cinema
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry

@Entity(
  tableName = "movie_registries",
  foreignKeys = [
    ForeignKey(
      entity = MovieDB::class,
      parentColumns = ["id"],
      childColumns = ["movie_id"],
      onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(
      entity = CinemaDB::class,
      parentColumns = ["id"],
      childColumns = ["cinema_id"],
      onDelete = ForeignKey.CASCADE
    )
  ],
  indices = [
    Index("movie_id"),
    Index("cinema_id")
  ]
)
data class MovieRegistryDB(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0,
  @ColumnInfo(name = "movie_id")
  var movieId: String = "",
  @ColumnInfo(name = "cinema_id")
  var cinemaId: Long = 0,
  var rate : Int = 0,
  var seen: String = "",
  var observations: String = ""
) {
  fun toMovieRegistry(cinema: Cinema, movie: Movie, images: List<RegistryImageDB>): MovieRegistry {
    return MovieRegistry(
      id = this.id,
      movie= movie,
      cinema = cinema,
      rate = this.rate,
      seen = this.seen,
      observations = this.observations,
      images = images.map { it.toRegistryImage() }
    )
  }
}
