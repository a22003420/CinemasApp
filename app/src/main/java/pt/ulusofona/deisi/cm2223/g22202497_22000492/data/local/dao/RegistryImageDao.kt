package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.*
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.RegistryImageDB

@Dao
interface RegistryImageDao {

  // Insere um objeto RegistryImageDB na base de dados
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRegistryImage(registryImage: RegistryImageDB)

  // Insere uma lista de objetos RegistryImageDB na base de dados
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRegistryImages(registryImages: List<RegistryImageDB>)

  // Retorna uma lista de objetos RegistryImageDB com base no ID do registro fornecido
  @Query("SELECT * FROM registry_images WHERE movie_registry_id = :registryId")
  fun getImagesForRegistry(registryId: Long): List<RegistryImageDB>

  // Deleta um objeto RegistryImageDB da base de dados
  @Delete
  fun deleteRegistryImage(registryImage: RegistryImageDB)
}
