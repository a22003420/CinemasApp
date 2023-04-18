import com.example.cinemas_app.model.Filme
import java.text.SimpleDateFormat
import java.util.*



object History {

  //private lateinit var imagem: Drawable

  //create list of history items
  val data1 = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse("2022/12/12")
  //imagem = ContextCompat.getDrawable(context, R.drawable.luzdodiabo)!!
  val filme1 = Filme(
    "1",
    "A Luz do Diabo",
    "Colombo",
    5,
    data1,
    "FOTO",
    "Filme de Terror muito bom, recomendo"
  )

  val data2 = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse("2022/04/15")
 // imagem = ContextCompat.getDrawable(context, R.drawable.troll)!!
  val filme2 = Filme(
    "2",
    "Troll",
    "Strada",
    6,
    data2,
    "FOTO",
    "Filme de Acção, recomendo"
  )

  val data3 = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).parse("2022/08/22")
  //imagem = ContextCompat.getDrawable(context, R.drawable.emancipation)!!
  val filme3 = Filme(
    "3",
    "Emancipation",
    "UBBO",
    7,
    data3,
    "FOTO",
    "Filme de Acção"
  )

  val historyItems = mutableListOf(filme1, filme2, filme3)

  fun getOperationById(uuid: String): Filme? {
    return historyItems.find { it.id == uuid }
  }
}