package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.CinemaDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieRegistryDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.RegistryImageDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie

// DAO (Data Access Object) para a entidade MovieRegistryDB
@Dao
interface MovieRegistryDao {

  // Insere um objeto MovieRegistryDB na base de dados e retorna o ID do objeto inserido na base de dados
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRegistry(registry: MovieRegistryDB): Long

  // Retorna uma lista com todos os registos na base de dados (MovieRegistryDB) ordenados por data de visualização
  @Query("SELECT * FROM movie_registries")
  fun getAllRegistries(): List<MovieRegistryDB>

  // Retorna um objeto MovieRegistryDB com base no ID do registo fornecido
  @Query("SELECT * FROM movie_registries WHERE id = :id")
  fun getRegistryById(id: Long): MovieRegistryDB

  // Insere uma lista de objetos MovieRegistryDB na base de dados
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addAllRegitries(registries: List<MovieRegistryDB>)

  // Retorna um objeto CinemaDB com base no ID do cinema fornecido
  @Query("SELECT * FROM cinemas WHERE id = :cinemaId")
  fun getCinemaFromRegistry(cinemaId: Long): CinemaDB

  // Retorna um objeto MovieDB com base no ID do filme fornecido
  @Query("SELECT * FROM movies WHERE id = :movieId")
  fun getMovieFromRegistry(movieId: String): MovieDB

  // Retorna um objeto MovieRegistryDB com base no ID do filme fornecido
  @Query("SELECT * FROM movie_registries WHERE movie_id = :movieId")
  fun getRegistryByMovieId(movieId: String): MovieRegistryDB

  @Update
  fun updateRegistry(registry: MovieRegistryDB)

}
