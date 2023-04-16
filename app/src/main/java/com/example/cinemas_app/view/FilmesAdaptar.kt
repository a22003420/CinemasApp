package com.example.cinemas_app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemas_app.R

class FilmesAdapter(private val filmes: List<Filmes>) :
    RecyclerView.Adapter<FilmesAdapter.FilmeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.itemdalistadefilmes, parent, false)
        return FilmeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        val currentFilme = filmes[position]
        holder.tituloFilme.text = currentFilme.titulo
        holder.descricaoFilme.text = currentFilme.ano.toString()
    }

    override fun getItemCount() = filmes.size

    inner class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloFilme: TextView = itemView.findViewById(R.id.filme_titulo)
        val descricaoFilme: TextView = itemView.findViewById(R.id.filme_classificacao)
    }
}
