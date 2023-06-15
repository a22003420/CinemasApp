package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities

import android.net.Uri
import androidx.room.*
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Cinema
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry
// Entidade MovieRegistryDB para a base de dados local (Room) que representa um registo de filme na base de dados local (Room)
@Entity(
  tableName = "movie_registries",
  foreignKeys = [
    // Chave estrangeira para a tabela movies (id) e cinemas (id) para garantir a integridade referencial
    ForeignKey(
      // Nome da coluna na tabela movie_registries que representa a chave estrangeira para a tabela movies
      entity = MovieDB::class,
      // Nome da coluna na tabela movies que representa a chave primária
      parentColumns = ["id"],
      // Nome da coluna na tabela movie_registries que representa a chave estrangeira para a tabela movies
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
  // Índices para melhorar a performance de queries que envolvam as colunas movie_id e cinema_id (chaves estrangeiras)
  indices = [
    Index("movie_id"),
    Index("cinema_id")
  ]
)

 // Entidade MovieRegistryDB para a base de dados local (Room) que representa um registo de filme na base de dados local (Room)
data class MovieRegistryDB(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0,
  @ColumnInfo(name = "movie_id")
  var movieId: String = "",
  @ColumnInfo(name = "cinema_id")
  var cinemaId: Long = 0,
  var rate : Int = 0,
  var seen: String = "",
  var isChecked: Boolean = false,
  var observations: String = "",
  var clickCount: Int = 0, // Count clicks filme
) {
  // Converte um objeto MovieRegistryDB em um objeto MovieRegistry (model)
  fun toMovieRegistry(cinema: Cinema, movie: Movie, images: List<RegistryImageDB>): MovieRegistry {
    return MovieRegistry(
      id = this.id,
      movie= movie,
      cinema = cinema,
      rate = this.rate,
      seen = this.seen,
      isChecked = this.isChecked,
      clickCount= this.clickCount,
      observations = this.observations,
      images = images.map { it.toRegistryImage() }
    )
  }
}
