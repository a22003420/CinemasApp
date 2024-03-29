package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject


object History {

  fun loadCinemas(context: Context): List<Cinema> {
    val jsonString = context.assets.open("cinemas.json").bufferedReader().use { it.readText() }
    val jsonObject = JSONObject(jsonString)
    val cinemasJsonArray = jsonObject.getJSONArray("cinemas")

    // test

    val cinemasList = mutableListOf<Cinema>()
    for (i in 0 until cinemasJsonArray.length()) {
      val cinemaJsonObject = cinemasJsonArray.getJSONObject(i)
      val cinema = Cinema(
        id = cinemaJsonObject.getLong("cinema_id"),
        name = cinemaJsonObject.getString("cinema_name"),
        provider = cinemaJsonObject.getString("cinema_provider"),
        latitude = cinemaJsonObject.getDouble("latitude"),
        longitude = cinemaJsonObject.getDouble("longitude"),
        address = cinemaJsonObject.getString("address"),
        county = cinemaJsonObject.getString("county"),
        postcode = cinemaJsonObject.getString("postcode"),
        photos = cinemaJsonObject.optString("photos").split(",")
      )
      cinemasList.add(cinema)
    }

    return cinemasList
  }

  fun getCinemaByName(paramCinemaList: List<Cinema>, name: String?): Cinema? {
    return paramCinemaList.find { it.name.equals(name, ignoreCase = true) }
  }
}