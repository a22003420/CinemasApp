package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB


@Dao
interface MovieOperations {

    @Insert
    fun insertMovie(character: MovieDB)


    @Query("SELECT * FROM movies")
    fun getAllMovies(): List<MovieDB>



    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): MovieDB


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieAll(characters: List<MovieDB>)

    @Delete
    fun deleteMovie(characters: List<MovieDB>)

    @Query("DELETE FROM movies")
    fun deleteAllMovies()


}