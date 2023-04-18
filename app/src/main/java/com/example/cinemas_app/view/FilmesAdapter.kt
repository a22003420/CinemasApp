package com.example.cinemas_app.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.databinding.MovieListItemBinding
import com.example.cinemas_app.model.Filme
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
      cinemaFilme.text = item.cinema
      classificacaoFilme.text = item.classificacao.toString()
      anoFilme.text = item.ano?.let { dateFormat.format(it) }
      //obterAnoDoFilme(item.nome)

    }
  }

  fun obterAnoDoFilme(nomeDoFilme: String): String {
    return when (nomeDoFilme) {
      "Emancipation" -> "2021"
      "Troll" -> "2022"
      else -> "2023"
    }
  }

  override fun getItemCount() = items.size

  fun updateItems(items: List<Filme>) {
    this.items = items
    notifyDataSetChanged()
  }
}
