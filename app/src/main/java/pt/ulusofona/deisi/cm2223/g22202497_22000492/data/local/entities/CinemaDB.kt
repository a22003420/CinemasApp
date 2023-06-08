package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cinemas")
data class CinemaDB (
  @PrimaryKey(autoGenerate = false)
  var id: Long,
  var name: String,
  var provider: String,
  var latitude: Double,
  var longitude: Double,
  var address: String,
  var county: String
)
