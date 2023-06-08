package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.CinemaDao
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.MovieDao
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.MovieRegistryDao
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.RegistryImageDao
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.CinemaDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieRegistryDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.RegistryImageDB

@Database(entities = [MovieDB::class, CinemaDB::class, MovieRegistryDB::class, RegistryImageDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao
  abstract fun cinemaDao(): CinemaDao
  abstract fun movieRegistryDao(): MovieRegistryDao
  abstract fun registryImageDao(): RegistryImageDao

  companion object {
    private var instance: AppDatabase? = null

    fun getInstance(applicationContext: Context): AppDatabase {
      synchronized(this) {
        if (instance == null) {
          instance = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_db"
          ).build()
        }
        return instance as AppDatabase
      }
    }
  }
}
