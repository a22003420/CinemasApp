package pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.CinemaOperations
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.MovieOperations
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.dao.MovieRegistryOperations
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.CinemaDB
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieRegistryDB

@Database(entities = [MovieDB::class, CinemaDB::class, MovieRegistryDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
  abstract fun movieOperations(): MovieOperations
  abstract fun cinemaOperations(): CinemaOperations
  abstract fun movieRegistryOperations(): MovieRegistryOperations

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
