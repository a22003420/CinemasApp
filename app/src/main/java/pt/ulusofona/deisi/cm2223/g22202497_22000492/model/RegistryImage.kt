package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import android.net.Uri
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.RegistryImageDB

data class RegistryImage(
  var id: Long = 0,
  var uri: String,
  var movieRegistryId: Long
) {
  fun toUri(): Uri {
    return Uri.parse(this.uri)
  }

  fun toRegistryImageDB(): RegistryImageDB {
    return RegistryImageDB(
      id = this.id,
      movieRegistryId = this.movieRegistryId,
      uri = this.uri
    )
  }
}
