package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities

import androidx.room.Entity

@Entity(tableName = "cinemas")
data class CinemaDB (
  val id: Int,
  val name: String,
  val provider: String,
  val latitude: Double,
  val longitude: Double,
  val address: String,
  val county: String
)
