package pt.ulusofona.deisi.cm2223.g22202497_22000492.data

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.remote.ConnectivityUtil
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieSource

// O LOTRRepository herda de LOTR e recebe dois models do tipo LOTR
// Um para a base de dados local (exercício) e outro para o web service
class MovieRepository (
    private val local: MovieSource,
    private val remote: MovieSource,
    private val context: Context
) : MovieSource() {

    override fun getCharacters(
        onFinished: (Result<List<Movie>>) -> Unit) {
        if (ConnectivityUtil.isOnline(context)) {
            // Se tenho acesso à Internet, vou buscar os registos ao web service
            // e atualizo a base de dados com os novos registos eliminando os
            // antigos, porque podem ter eliminado personagens do web service

            remote.getCharacters { result ->
                if (result.isSuccess) {
                    result.getOrNull()?.let { characters ->
                        // Se tiver entra aqui
                        Log.i("APP", "Got ${characters.size} characters from the server")

                        onFinished(Result.success(characters))
                        local.clearAllCharacters {
                            Log.i("APP", "Cleared DB")
                            local.insertCharacters(characters) {
                                onFinished(Result.success(characters))
                            }
                        }
                    }
                } else {
                    Log.w("APP", "Error getting characters from server")
                    onFinished(result)  // propagate the remote failure
                }
            }

        } else {
            // O que fazer se não houver Internet?
            // Devolver os que estão guardados na base de dados
            Log.i("APP", "App is offline. Getting characters from the database")
            local.getCharacters(onFinished)
        }
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: MovieRepository? = null

        // Temos de executar o init antes do getInstance
        fun init(local: MovieSource, remote: MovieSource, context: Context) {
            synchronized(this) {
                if (instance == null) {
                    instance = MovieRepository(remote, local, context)
                }
            }
        }

        fun getInstance(): MovieRepository {
            if (instance == null) {
                // Primeiro temos de invocar o init, se não lança esta Exception
                throw IllegalStateException("singleton not initialized")
            }
            return instance as MovieRepository
        }

    }

    override fun insertCharacters(characters: List<Movie>, onFinished: () -> Unit) {
        throw Exception("Illegal operation")
    }

    override fun clearAllCharacters(onFinished: () -> Unit) {
        throw Exception("Illegal operation")
    }

}
