package com.example.cinemas_app.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.databinding.ItemdalistadefilmesBinding

class FilmesAdapter(
    private val onClick: (String) -> Unit,
    private var items: List<Filmes> = listOf()
) : RecyclerView.Adapter<FilmesAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(val binding: ItemdalistadefilmesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            ItemdalistadefilmesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        ))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onClick(items[position].id) }
        holder.binding.filmeTitulo.text = items[position].nome
        holder.binding.filmeClassificacao.text = items[position].observacoes
    }

    override fun getItemCount() = items.size

    fun updateItems(items: List<Filmes>) {
        this.items = items
        notifyDataSetChanged()
    }
}
