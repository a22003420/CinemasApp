package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemas_app.databinding.MovieListItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.MovieRegistry

class MovieAdapter(
  private val onClick: (String) -> Unit,
  private var items: List<MovieRegistry>
) : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {

  class MoviesViewHolder(val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root)

  fun setMovieList(newMovieList: List<MovieRegistry>) {
    items = newMovieList

    CoroutineScope(Dispatchers.Main).launch {
      notifyDataSetChanged()
    }
  }

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
      nomeFilme.text = item.movie.name
      classificacaoFilme.text = item.movie.imdbRating
      anoFilme.text = item.movie.releaseDate

      Glide.with(holder.itemView)
        .load(item.movie.photo)
        .into(filmeImagem)
    }
  }

  override fun getItemCount() = items.size
}
