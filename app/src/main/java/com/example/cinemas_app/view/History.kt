import com.example.cinemas_app.model.Filme
import java.text.SimpleDateFormat
import java.util.*



object History {
  val movieList = generateInitialMovieList()
  fun getOperationById(uuid: String): Filme? {
    return movieList.find { it.id == uuid }
  }
  private fun generateInitialMovieList() : MutableList<Filme> {
    return mutableListOf(
      Filme(
        "1",
        "A Luz do Diabo",
        "Colombo",
        5,
        genDate("2022/12/12"),
        "FOTO",
        "Filme de Terror muito bom, recomendo"
      ),
      Filme(
        "2",
        "Troll",
        "Strada",
        6,
        genDate("2022/04/15"),
        "FOTO",
        "Filme de Acção, recomendo"
      ),
      Filme(
        "3",
        "Emancipation",
        "UBBO",
        7,
        genDate("2022/08/22"),
        "FOTO",
        "Filme de Acção"
      )
    )
  }

  private fun genDate(srcDate : String) : Date {
    return SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse("2022/08/22")
  }
}