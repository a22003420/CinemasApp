package pt.ulusofona.deisi.cm2223.g22202497_22000492.view.adapters


import android.annotation.SuppressLint
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemas_app.R
import com.example.cinemas_app.databinding.MovieListItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.MovieRepository
import pt.ulusofona.deisi.cm2223.g22202497_22000492.data.local.AppDatabase
import pt.ulusofona.deisi.cm2223.g22202497_22000492.model.Movie
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
    val item = items[position]





    holder.itemView.setOnClickListener {
      var clickCount = item.clickCount + 1
      item.clickCount = clickCount
      MovieRepository.getInstance().updateRegistry(item) {
      }
      onClick(items[position].id.toString())

    }

    holder.binding.apply {
      nomeFilme.text = item.movie.name
      classificacaoFilme.text = item.movie.imdbRating
      anoFilme.text = item.movie.releaseDate
      pcode.text = item.cinema.postcode
      //foto.text = item.cinema



      //grauSatisfacao.text=item.rate.toString()

      //grauSatisfacao.text = when (item.rate) {
      //in 3..4 -> "FRACO"
      //in 7..8 -> "BOM"
      //else -> item.rate.toString()
      //}



      Glide.with(holder.itemView)
        .load(item.movie.photo)
        .into(filmeImagem)
    }

    // set background color in each item
    //holder.binding.movieItem.setBackgroundColor(
    //if (position % 2 == 0) {
    //  ContextCompat.getColor(holder.itemView.context, R.color.blue)
    //} else {
    //  ContextCompat.getColor(holder.itemView.context, R.color.wine_red)
    //}
    //)



    //sort list of movies by year of release in descending order
    //items = items.sortedByDescending {
    //it.rate
    //}
  }



override fun getItemCount() = items.size
}

