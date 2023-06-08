package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.remote

import okhttp3.*
import org.json.JSONObject
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.RemoteOps
import pt.ulusofona.deisi.cm2223.g22202497_22000492.requestUrl
import java.io.IOException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MoviesOkHttp (
  private val client: OkHttpClient
  ): RemoteOps() {

  override fun searchMovie(
    movieName: String,
    movieYear: Int,
    onFinished: (Result<Movie>) -> Unit
  ) {

    val finalUrl = "$requestUrl&t=${urlEncodeString(movieName)}&y=$movieYear"

    imdbRequest(finalUrl, onFinished)
  }

  override fun getMovie(id: String, onFinished: (Result<Movie>) -> Unit) {
    val finalUrl = "$requestUrl&i=$id"

    imdbRequest(finalUrl, onFinished)
  }

  private fun imdbRequest(
    requestLink: String,
    onFinished: (Result<Movie>) -> Unit
  ) {

    val request: Request = Request.Builder()
      .url(requestLink)
      .build()


    client.newCall(request).enqueue(object : Callback {

      override fun onFailure(call: Call, e: IOException) {
        onFinished(Result.failure(e))
      }


      override fun onResponse(call: Call, response: Response) {
        response.apply {
          if (!response.isSuccessful) {
            onFinished(Result.failure(IOException("Unexpected code $response")))
          } else {
            val body = response.body?.string()
            if (body != null) {
              val jsonObject = JSONObject(body)

              if (jsonObject.getString("Response") == "False") {
                onFinished(
                  Result.failure(
                    Exception(
                      jsonObject.getString("Error")
                    )
                  )
                )
                return
              }

              val movie = Movie(
                jsonObject.getString("imdbID"),
                jsonObject.getString("Title"),
                jsonObject.getInt("Year"),
                jsonObject.getString("Poster"),
                jsonObject.getString("Genre"),
                jsonObject.getString("Plot"),
                jsonObject.getString("Released"),
                jsonObject.getString("imdbRating"),
                "https://www.imdb.com/title/${jsonObject.getString("imdbID")}"
              )

              onFinished(Result.success(movie))
            }
          }
        }
      }
    })
  }

  private fun urlEncodeString(input: String): String {
    return URLEncoder.encode(input, StandardCharsets.UTF_8.toString())
  }
}
