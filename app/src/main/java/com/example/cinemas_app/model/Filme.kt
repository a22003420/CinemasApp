package com.example.cinemas_app.model

import java.util.*

data class Filme(
  val id: String = UUID.randomUUID().toString(),
  val nome: String,
  val cinema: String,
  val classificacao: Int,
  val ano: Int,
  val visto: Date,
  val observacoes: String,
) {

}
