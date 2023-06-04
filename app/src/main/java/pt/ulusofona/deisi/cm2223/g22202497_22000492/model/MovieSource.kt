package pt.ulusofona.deisi.cm2223.g22202497_22000492.model

abstract class MovieSource {
    abstract fun getCharacters(onFinished: (Result<List<Movie>>) -> Unit)
    abstract fun insertCharacters(characters: List<Movie>, onFinished: () -> Unit)
    abstract fun clearAllCharacters(onFinished: () -> Unit)
}