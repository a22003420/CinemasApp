package pt.ulusofona.deisi.cm2223.g22202497_22000492

import android.app.Application
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.MovieRoom
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.AppDatabase
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.remote.MoviesOkHttp
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.RemoteOps

class MovieApplication  : Application() {

  override fun onCreate() {
    super.onCreate()
    MovieRepository.init(
      local = MovieRoom(
        AppDatabase.getInstance(this).movieDao(),
        AppDatabase.getInstance(this).movieRegistryDao(),
        AppDatabase.getInstance(this).registryImageDao(),
        AppDatabase.getInstance(this).cinemaDao(),
        this
      ),
      remote = MoviesOkHttp(client = OkHttpClient()),
      context = this
    )
    Log.i("APP", "Initialized repository")
  }
}
