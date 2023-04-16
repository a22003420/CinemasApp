package com.example.cinemas_app.view

import java.util.*

data class Filmes(
    val id: String = UUID.randomUUID().toString(), //gera um id unico, temos de alterar para
                                                // o counter, para não ser gerado um ids repetidos
    val nome: String,
    val cinema: String,
    val classificacao: Int,
    val ano: Date?,
    //val imagem: Drawable?, // falta implementar
    val imagem: String,
    val observacoes: String,

    )
