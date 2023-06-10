package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.databinding.MovieListItemBinding
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie

class MovieAdapter(
  private val onClick: (String) -> Unit,
  private var items: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {

  class MoviesViewHolder(val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
    return MoviesViewHolder(
      MovieListItemBinding.inflate(
        LayoutInflater.from(parent.context),
        parent, false
      ))
  }

  override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
    holder.itemView.setOnClickListener { onClick(items[position].id.toString()) }
    val item = items[position]

    holder.binding.apply {
      nomeFilme.text = item.name
      classificacaoFilme.text = item.imdbRating
      anoFilme.text = item.releaseDate

      val resourceId = holder.itemView.context.resources.getIdentifier(item.photo, "drawable",  holder.itemView.context.packageName)
      filmeImagem.setImageResource(resourceId)
    }
  }

  override fun getItemCount() = items.size
}
