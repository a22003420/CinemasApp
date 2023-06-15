package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.CinemaDB

// DAO (Data Access Object) para a entidade CinemaDB
@Dao
interface CinemaDao {

  // Insere um objeto CinemaDB na base de dados
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertCinema(cinema: CinemaDB)

  // Retorna uma lista com todos os cinemas na base de dados
  @Query("SELECT * FROM cinemas")
  fun getAllCinemas(): List<CinemaDB>

  // Retorna um objeto CinemaDB com base no ID fornecido
  @Query("SELECT * FROM cinemas WHERE id = :id")
  fun getCinemaById(id: Long): CinemaDB

  // Retorna um objeto CinemaDB com base no nome fornecido
  @Query("SELECT * FROM cinemas WHERE name = :name")
  fun getCinemaByName(name: String): CinemaDB?

  // Insere uma lista de objetos CinemaDB na base de dados
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun addAllCinemas(cinemas: List<CinemaDB>)
}
