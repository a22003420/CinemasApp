package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.remote

import android.util.Log
import com.google.gson.annotations.SerializedName
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import pt.ulusofona.deisi.cm2223.g22202497_22000492.IMDB_API_BASE_URL
import pt.ulusofona.deisi.cm2223.g22202497_22000492.IMDB_API_KEY
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieSource
import pt.ulusofona.deisi.cm2223.g22202497_22000492.requestUrl
import java.io.IOException

class MoviesServiceOkHttpAndJsonObject (val baseUrl: String = IMDB_API_BASE_URL,
                  val client: OkHttpClient) : MovieSource() {

    override fun insertCharacters(characters: List<Movie>, onFinished: () -> Unit) {
        throw Exception("Illegal operation")
    }

    override fun clearAllCharacters(onFinished: () -> Unit) {
        Log.e("APP", "web service is not able to clear all characters")
    }

    override fun getCharacters(onFinished: (Result<List<Movie>>) -> Unit) {

        // Aqui estamos a preparar o pedido. Precisamos da apiKey e do url
        val request: Request = Request.Builder()
            .url(requestUrl)
            .build()

        // Continua no próximo quadro

        // Nesta linha executamos o pedido ao servidor
        // em caso caso de erro, o método onFailure será invocado (ex: timeout)
        // se tudo correr bem, teremos a resposta ao pedido no método onResponse
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                onFinished(Result.failure(e))
            }

            // Processar a resposta ao pedido
            override fun onResponse(call: Call, response: Response) {
                response.apply {
                    // Se a resposta devolver um erro, ex: 403 acesso negado ao web service
                    if (!response.isSuccessful) {
                        onFinished(Result.failure(IOException("Unexpected code $response")))
                    } else {
                        val body = response.body?.string()
                        if (body != null) {
                            // Estamos a guardar o objeto assinalado a amarelo no exemplo aqui
                            val jsonObject = JSONObject(body)
                            // Aqui vamos guardar o array ainda em formato json
                            val jsonCharactersList = jsonObject["docs"] as JSONArray
                            val movieCharacters = mutableListOf<Movie>()

                            for (i in 0 until jsonCharactersList.length()) {
                                val jsonCharacter = jsonCharactersList.getJSONObject(i)
                                movieCharacters.add(
                                    Movie(
                                        jsonCharacter.getString("imdbID"),
                                        jsonCharacter.getString("Title"),
                                        jsonCharacter.getString("Poster"),
                                        jsonCharacter.getString("Genre"),
                                        jsonCharacter.getString("Plot"),
                                        jsonCharacter.getString("Released"),
                                        jsonCharacter.getString("imdbRating"),
                                        jsonCharacter.getString("DVD")
                                    )

                                )
                                Log.i("test1", "Passou atrás")
                            }

                            // Devolve a lista de personagens já com em objetos Kotlin
                            onFinished(Result.success(movieCharacters))
                            Log.i("test1", "Passou aqui")

                        }
                    }
                }
            }
        })
    }
}
