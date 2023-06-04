package pt.ulusofona.deisi.cm2223.g22202497_22000492

import android.app.Application
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.MovieDBWithRoom
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.MoviesDatabase
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.remote.MoviesServiceOkHttpAndJsonObject
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieSource

class MovieApplication  : Application() {

    override fun onCreate() {
        super.onCreate()
        MovieRepository.init(
            local = MovieDBWithRoom(MoviesDatabase.getInstance(this).movieOperations()),
            remote = MoviesServiceOkHttpAndJsonObject(client = OkHttpClient()),
            context = this
        )
        Log.i("APP", "Initialized repository")
        val model: MovieSource = MovieRepository.getInstance()
        CoroutineScope(Dispatchers.IO).launch {
            model.getCharacters { result ->
                result.onSuccess { remoteData ->
                    MoviesDatabase.getInstance(this@MovieApplication)?.let { database ->
                        val movieDBWithRoom = MovieDBWithRoom(database.movieOperations())
                        movieDBWithRoom.clearAllCharacters {
                            movieDBWithRoom.insertCharacters(remoteData) {

                            }
                        }
                    }
                }
                result.onFailure { exception ->

                    Log.e("APP", "Failed to get movie characters: ${exception.message}")
                }
            }

        }
    }

}




            /* model.getCharacters {  result ->
    result.onSuccess { remoteData ->
        //
        MoviesDatabase.prepopulateDatabase(
            MoviesDatabase.getInstance(this@MovieApplication),
            remoteData
        )
    }
    result.onFailure { exception ->
        // error
        Log.e("APP", "Failed to get movie characters: ${exception.message}")
    }
} */
