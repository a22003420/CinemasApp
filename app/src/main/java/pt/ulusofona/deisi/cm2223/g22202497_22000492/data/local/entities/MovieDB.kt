package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieDB(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,

    @ColumnInfo(name = "movie_id")
    val movieId: String, // movieId corresponds to Movie.id
    val name: String,
    val photo: String,
    val genre: String,
    val synopsis: String,
    val releaseDate: String,
    val imdbRating: String,
    val imdbLink: String
)

