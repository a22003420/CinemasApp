package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cinema (
  @SerializedName("cinema_id")
  val id: Long,
  @SerializedName("cinema_name")
  val name: String,
  @SerializedName("cinema_provider")
  val provider: String,
  val latitude: Double,
  val longitude: Double,
  val address: String,
  val county: String
) : Serializable
