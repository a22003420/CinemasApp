package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.entities.MovieRegistryDB

abstract class LocalOps {
  abstract fun getMovies(onFinished: (Result<List<Movie>>) -> Unit)
  abstract fun updateMovie(movie: Movie, onFinished: () -> Unit)
  abstract fun getMovieById(id: String, onFinished: (Result<Movie>) -> Unit)
  abstract fun insertMovies(movies: List<Movie>, onFinished: () -> Unit)
  abstract fun insertMovie(movie: Movie, onFinished: (Result<Movie>) -> Unit)
  abstract fun clearAllMovies(onFinished: () -> Unit)
  abstract fun deleteRegistryImage(registryImage: RegistryImage, onFinished: () -> Unit)
  abstract fun insertRegistry(registry: MovieRegistry, onFinished: (Result<MovieRegistry>) -> Unit)
}
