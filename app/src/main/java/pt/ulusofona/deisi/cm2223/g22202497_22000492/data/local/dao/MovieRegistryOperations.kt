package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieRegistryDB

@Dao
interface MovieRegistryOperations {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addRegistry(character: MovieRegistryDB)

  @Query("SELECT * FROM movie_registries")
  fun getAllRegistries(): List<MovieRegistryDB>


  @Query("SELECT * FROM movie_registries WHERE id = :id")
  fun getRegistryById(id: Int): MovieRegistryDB

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addAllRegitries(characters: List<MovieRegistryDB>)
}
