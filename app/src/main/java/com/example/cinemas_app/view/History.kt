import com.example.cinemas_app.model.Filme
import java.text.SimpleDateFormat
import java.util.*



object History {
  val movieList = generateInitialMovieList()
  fun getOperationById(uuid: String): Filme? {
    return movieList.find { it.id == uuid }
  }

  fun top5RatedMovies(): List<Filme> {
    return movieList.sortedByDescending { it.classificacao }.take(5)
  }

  fun top5RecentMovies(): List<Filme> {
    return movieList.sortedByDescending { it.ano }.take(5)
  }

  fun top5LastSeenMovies(): List<Filme> {
    return movieList.sortedByDescending { it.visto }.take(5)
  }

  private fun generateInitialMovieList() : MutableList<Filme> {
    return mutableListOf(
      Filme(
        "1",
        "A Luz do Diabo",
        "Colombo",
        5,
        2022,
        genDate("2022/12/12"),
        "Filme de Terror muito bom, recomendo"
      ),
      Filme(
        "2",
        "Troll",
        "Strada",
        6,
        2022,
        genDate("2022/04/15"),
        "Filme de Acção, recomendo"
      ),
      Filme(
        "3",
        "Emancipation",
        "UBBO",
        7,
        2021,
        genDate("2022/08/22"),
        "Filme de Acção"
      )
    )
  }

  private fun genDate(srcDate : String) : Date {
    return SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse(srcDate)
  }
}