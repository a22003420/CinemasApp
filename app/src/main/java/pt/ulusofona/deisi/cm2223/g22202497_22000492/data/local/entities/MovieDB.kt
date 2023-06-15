package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie

// Entidade MovieDB para a base de dados local (Room) que representa um filme na base de dados local (Room)
@Entity(tableName = "movies")
data class MovieDB(
  @PrimaryKey(autoGenerate = false)
  var id: String,
  var name: String,
  var year: String,
  var photo: String,
  var genre: String,
  var synopsis: String,
  var imdbRating: String,
  var releaseDate: String,
  var dvd: String,
  var imdbLink: String
){

  // Converte um objeto MovieDB em um objeto Movie (model)
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
      dvd = this.dvd,
      imdbLink = this.imdbLink
    )
  }
}

//1-Coloca apenas marcadores (markers/pins) mapa nos filmes registados que tenham o género "action"
//(os restantes nao deverão aparecer)- Cars 2, cars3, american pie

//2- No ecra de detalhe apresenta o texto Lista ou Mapa de acordo que levou o utilizador ao ecra de detalhe.
//Se veio da lista de filmes deve aparecer o texto Lista, se veio do mapa deve aparecer o texto Mapa.

//3- Apresenta no elemento da lista o numero da fotografias que esse cinemas tem registadas. JSON (este n corre deve ser do array do jason)


