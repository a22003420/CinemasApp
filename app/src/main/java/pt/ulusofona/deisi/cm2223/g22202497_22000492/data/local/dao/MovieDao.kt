package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieRegistryDB

// DAO (Data Access Object) para a entidade MovieDB
@Dao
interface MovieDao {

  // Insere um objeto MovieDB na base de dados (se já existir, substitui)
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovie(movie: MovieDB)

  // Atualiza um objeto MovieDB na base de dados (se não existir, insere)
  @Update
  fun updateMovie(movie: MovieDB)

  // Retorna uma lista com todos os filmes da base de dados (se não existir, retorna uma lista vazia)
  @Query("SELECT * FROM movies")
  fun getAllMovies(): List<MovieDB>

  // Retorna um objeto MovieDB com base no ID do filme fornecido
  @Query("SELECT * FROM movies WHERE id = :movieId")
  fun getMovieById(movieId: String): MovieDB?

  // Retorna um objeto MovieDB com base no nome do filme fornecido (se não existir, retorna null)
  @Query("SELECT * FROM movies WHERE name = :movieName")
  fun getMovieByName(movieName: String): MovieDB?

  // Insere uma lista de objetos MovieDB na base de dados (se já existirem, substitui)
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovieAll(movies: List<MovieDB>)

  // Deleta uma lista de objetos MovieDB na base de dados
  @Delete
  fun deleteMovie(movies: List<MovieDB>)

  // Deleta todos os filmes da base de dados
  @Query("DELETE FROM movies")
  fun deleteAllMovies()

  // Retorna um objeto MovieRegistryDB com base no ID do filme fornecido
  @Query("SELECT * FROM movie_registries WHERE movie_id = :movieId")
  fun getMovieFromRegistry(movieId: String): MovieRegistryDB?

}
