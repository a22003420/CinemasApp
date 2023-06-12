package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieRegistryDB


@Dao
interface MovieDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovie(movie: MovieDB)

  @Update
  fun updateMovie(movie: MovieDB)

  @Query("SELECT * FROM movies")
  fun getAllMovies(): List<MovieDB>

  @Query("SELECT * FROM movies WHERE id = :movieId")
  fun getMovieById(movieId: String): MovieDB?

  @Query("SELECT * FROM movies WHERE name = :movieName")
  fun getMovieByName(movieName: String): MovieDB?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMovieAll(movies: List<MovieDB>)

  @Delete
  fun deleteMovie(movies: List<MovieDB>)

  @Query("DELETE FROM movies")
  fun deleteAllMovies()

  @Query("SELECT * FROM movie_registries WHERE movie_id = :movieId")
  fun getMovieFromRegistry(movieId: String): MovieRegistryDB?
}
