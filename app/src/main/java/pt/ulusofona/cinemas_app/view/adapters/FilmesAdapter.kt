package pt.ulusofona.cinemas_app.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.databinding.MovieListItemBinding
import pt.ulusofona.cinemas_app.model.Filme
import java.text.SimpleDateFormat
import java.util.*

class FilmesAdapter(
  private val onClick: (String) -> Unit,
  private var items: List<Filme> = listOf()
) : RecyclerView.Adapter<FilmesAdapter.HistoryViewHolder>() {

  class HistoryViewHolder(val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
    return HistoryViewHolder(
      MovieListItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent, false
      ))
  }

  override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
    holder.itemView.setOnClickListener { onClick(items[position].id) }
    val item = items[position]
    val dateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
    holder.binding.apply {
      nomeFilme.text = item.nome
      classificacaoFilme.text = item.classificacao.toString()
      anoFilme.text = item.ano.toString()
    }
  }
  
  override fun getItemCount() = items.size

  fun updateItems(items: List<Filme>) {
    this.items = items
    notifyDataSetChanged()
  }
}
