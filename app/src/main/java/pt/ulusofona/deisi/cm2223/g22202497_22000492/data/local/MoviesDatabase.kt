package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.MovieOperations
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie

@Database(entities = [MovieDB::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieOperations(): MovieOperations

    companion object {
        private var instance: MoviesDatabase? = null

        fun getInstance(applicationContext: Context): MoviesDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext,
                        MoviesDatabase::class.java,
                        "filmes_db"
                    ).build()
                }
                return instance as MoviesDatabase
            }
        }



        /*fun prepopulateDatabase(database: MoviesDatabase, remoteData: List<Movie>) {
            // Limpar tudo na DB
            database.movieOperations().deleteAllMovies()

            // Maping movies from remote data to DB
            val apiMovies = remoteData.map {
                MovieDB(
                    movieId = it.id,
                    name = it.name,
                    photo = it.photo,
                    genre = it.genre,
                    synopsis = it.synopsis,
                    releaseDate = it.releaseDate,
                    imdbRating = it.imdbRating,
                    imdbLink = it.imdbLink
                )
            }

            // add movies hardcoded to DB as well
            val hardcodedMovies = listOf(
                MovieDB(
                    movieId = "1",
                    name = "Hardcoded Movie 1",
                    photo = "photo_url_1",
                    genre = "Action",
                    synopsis = "Synopsis 1",
                    releaseDate = "2023-05-20",
                    imdbRating = 7.5,
                    imdbLink = "https://www.imdb.com/movie1"
                ),
                MovieDB(
                    movieId = "2",
                    name = "Hardcoded Movie 2",
                    photo = "photo_url_2",
                    genre = "Comedy",
                    synopsis = "Synopsis 2",
                    releaseDate = "2023-05-21",
                    imdbRating = 8.0,
                    imdbLink = "https://www.imdb.com/movie2"
                )
            )
            val allMovies = apiMovies.toMutableList().apply { addAll(hardcodedMovies) }

            // Insert all on DB
            database.movieOperations().insertMovieAll(allMovies)
        }*/


    }
}
