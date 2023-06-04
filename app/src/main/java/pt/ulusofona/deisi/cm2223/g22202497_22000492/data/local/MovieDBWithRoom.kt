package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.MovieOperations
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieSource


class MovieDBWithRoom(
    private val storage: MovieOperations
) : MovieSource() {


    override fun getCharacters(onFinished: (Result<List<Movie>>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val characters = storage.getAllMovies().map {
                Movie(
                    id = it.movieId,
                    name = it.name,
                    photo = it.photo,
                    genre = it.genre,
                    synopsis = it.synopsis,
                    releaseDate = it.releaseDate,
                    imdbRating = it.imdbRating,
                    imdbLink = it.imdbLink
                )


            }
            onFinished(Result.success(characters))
        }
    }


    override fun insertCharacters(characters: List<Movie>, onFinished: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            /*val hardcodedMovies = listOf(
                MovieDB(
                    movieId = "1",
                    name = "Hardcoded Movie 1",
                    photo = "photo_url_1",
                    genre = "Action",
                    synopsis = "Synopsis 1",
                    releaseDate = "2023-05-20",
                    imdbRating = "10.0",
                    imdbLink = "https://www.imdb.com/movie1"
                ),
                MovieDB(
                    movieId = "2",
                    name = "Hardcoded Movie 2",
                    photo = "photo_url_2",
                    genre = "Comedy",
                    synopsis = "Synopsis 2",
                    releaseDate = "2023-05-21",
                    imdbRating = "10.1",
                    imdbLink = "https://www.imdb.com/movie2"
                )
            )*/

            characters.map {
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
            }//.plus(hardcodedMovies)
                .forEach {
                    storage.insertMovie(it)
                    Log.i("APP", "Was inserted ${it.name} in Database")
                }

            onFinished()
        }
    }



    override fun clearAllCharacters(onFinished: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            storage.deleteAllMovies()
            onFinished()
        }
    }
}
