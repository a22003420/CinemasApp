package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.RegistryImage

@Entity(tableName = "registry_images",
  foreignKeys = [
    ForeignKey(
      entity = MovieRegistryDB::class,
      parentColumns = ["id"],
      childColumns = ["movie_registry_id"],
      onDelete = ForeignKey.CASCADE
    )
  ]
)
data class RegistryImageDB(
  @PrimaryKey(autoGenerate = true)
  var id: Long = 0,
  @ColumnInfo(name = "movie_registry_id")
  var movieRegistryId: Long,
  var uri: String
) {
  fun toRegistryImage(): RegistryImage {
    return RegistryImage(
      id = this.id,
      uri = this.uri,
      movieRegistryId = this.movieRegistryId
    )
  }

  fun toUri(): Uri {
    return Uri.parse(this.uri)
  }
}
