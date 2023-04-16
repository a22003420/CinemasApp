package com.example.cinemas_app.view

import java.util.*

data class Filmes(
    val id: String = UUID.randomUUID().toString(),
    val nome: String,
    val cinema: String,
    val classificacao: Int,
    val ano: String,
    val imagem: Int,
    val observacoes: String,

)

