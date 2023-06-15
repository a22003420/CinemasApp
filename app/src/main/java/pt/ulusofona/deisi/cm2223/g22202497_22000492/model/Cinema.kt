package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import com.google.gson.annotations.SerializedName
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.CinemaDB
import java.io.Serializable

data class Cinema (
  @SerializedName("cinema_id")
  val id: Long = 0,
  @SerializedName("cinema_name")
  val name: String = "",
  @SerializedName("cinema_provider")
  val provider: String = "",
  val latitude: Double = 0.0,
  val longitude: Double = 0.0,
  val address: String = "",
  val county: String = "",
  val postcode: String = "",
  val  photos: List<String> = emptyList()

// array strings
) : Serializable
{
  fun toCinemaDB(): CinemaDB {
    return CinemaDB(
      id = this.id,
      name = this.name,
      provider = this.provider,
      latitude = this.latitude,
      longitude = this.longitude,
      address = this.address,
      county = this.county,
      postcode = this.postcode,
      photos = this.photos

    )
  }
}
