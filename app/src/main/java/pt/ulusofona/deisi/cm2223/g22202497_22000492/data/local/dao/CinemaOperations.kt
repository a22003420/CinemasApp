package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.CinemaDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB


@Dao
interface CinemaOperations {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addCinema(character: CinemaDB)

  @Query("SELECT * FROM cinemas")
  fun getAllCinemas(): List<CinemaDB>


  @Query("SELECT * FROM cinemas WHERE id = :id")
  fun getCinemaById(id: Int): CinemaDB

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addAllCinemas(characters: List<CinemaDB>)
}
