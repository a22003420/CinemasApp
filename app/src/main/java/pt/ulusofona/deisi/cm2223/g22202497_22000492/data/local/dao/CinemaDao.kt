package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.CinemaDB


@Dao
interface CinemaDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addCinema(cinema: CinemaDB)

  @Query("SELECT * FROM cinemas")
  fun getAllCinemas(): List<CinemaDB>

  @Query("SELECT * FROM cinemas WHERE id = :id")
  fun getCinemaById(id: Long): CinemaDB

  @Query("SELECT * FROM cinemas WHERE name = :name")
  fun getCinemaByName(name: String): CinemaDB?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addAllCinemas(cinemas: List<CinemaDB>)
}
