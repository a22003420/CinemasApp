package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.RegistryImageDB

@Dao
interface RegistryImageDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertRegistryImage(registryImage: RegistryImageDB)

  @Delete
  fun deleteRegistryImage(registryImage: RegistryImageDB)
}