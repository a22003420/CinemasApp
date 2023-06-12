package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie

@Entity(tableName = "movies")
data class MovieDB(
  @PrimaryKey(autoGenerate = false)
  var id: String,
  var name: String,
  var year: String,
  var photo: String,
  var genre: String,
  var synopsis: String,
  var releaseDate: String,
  var imdbRating: String,
  var imdbLink: String
){
  fun toMovie(): Movie {
    return Movie(
      id = this.id,
      name = this.name,
      year = this.year,
      photo = this.photo,
      genre = this.genre,
      synopsis = this.synopsis,
      releaseDate = this.releaseDate,
      imdbRating = this.imdbRating,
      imdbLink = this.imdbLink
    )
  }
}

