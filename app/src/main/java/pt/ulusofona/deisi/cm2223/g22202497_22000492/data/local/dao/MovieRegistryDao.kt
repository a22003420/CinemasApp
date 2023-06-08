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

@Dao
interface MovieRegistryDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRegistry(registry: MovieRegistryDB)

  @Query("SELECT * FROM movie_registries")
  fun getAllRegistries(): List<MovieRegistryDB>


  @Query("SELECT * FROM movie_registries WHERE id = :id")
  fun getRegistryById(id: Int): MovieRegistryDB

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addAllRegitries(registries: List<MovieRegistryDB>)

  @Query("SELECT * FROM registry_images WHERE movie_registry_id = :registryId")
  fun getImagesForRegistry(registryId: Long): List<RegistryImageDB>

  @Query("SELECT * FROM cinemas WHERE id = :cinemaId")
  fun getCinemaFromRegistry(cinemaId: Long): CinemaDB

  @Query("SELECT * FROM movies WHERE id = :movieId")
  fun getMovieFromRegistry(movieId: String): MovieDB
}
