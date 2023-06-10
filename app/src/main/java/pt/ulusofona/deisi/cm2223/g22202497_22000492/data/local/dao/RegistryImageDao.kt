package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.*
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.RegistryImageDB

@Dao
interface RegistryImageDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRegistryImage(registryImage: RegistryImageDB)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRegistryImages(registryImages: List<RegistryImageDB>)

  @Query("SELECT * FROM registry_images WHERE movie_registry_id = :registryId")
  fun getImagesForRegistry(registryId: Long): List<RegistryImageDB>

  @Delete
  fun deleteRegistryImage(registryImage: RegistryImageDB)
}