package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Cinema

// Entidade CinemaDB para a base de dados local (Room) que representa um cinema na base de dados local (Room)
@Entity(tableName = "cinemas")
data class CinemaDB(
    @PrimaryKey(autoGenerate = false)
    var id: Long,
    var name: String,
    var provider: String,
    var latitude: Double,
    var longitude: Double,
    var address: String,
    var county: String,
    var postcode: String,
    val photos: String
) {
  // Converte um objeto CinemaDB em um objeto Cinema (model)
  fun toCinema(): Cinema {
    return Cinema(
      id = this.id,
      name = this.name,
      provider = this.provider,
      latitude = this.latitude,
      longitude = this.longitude,
      address = this.address,
      county = this.county,
      postcode =this.postcode,
      photos = this.photos.split(",").toList()
    )
  }
}
