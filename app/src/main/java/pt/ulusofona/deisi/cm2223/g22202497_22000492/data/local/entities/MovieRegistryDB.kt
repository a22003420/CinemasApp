package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities

import android.net.Uri
import androidx.room.*

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
  val id: Long = 0,
  @ColumnInfo(name = "movie_id")
  val movieId: Long = 0,
  @ColumnInfo(name = "cinema_id")
  val cinemaId: Long = 0,
  val rate : Int = 0,
  val seen: String = "",
  val observations: String = "",
  var images: List<Uri> = listOf()
)