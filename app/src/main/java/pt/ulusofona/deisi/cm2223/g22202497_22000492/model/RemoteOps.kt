package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

abstract class RemoteOps {
    abstract fun searchMovie(movieName: String, movieYear: Int, onFinished: (Result<Movie>) -> Unit)
    abstract fun getMovie(id: String, onFinished: (Result<Movie>) -> Unit)
}
