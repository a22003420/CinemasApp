package com.example.cinemas_app.model

import java.util.*

class MovieRegistry(
    val movieId: Int,
    val cinema: String,
    val rate : Int,
    val seen: String,
    val observations: String) {

    fun getSeenDate(): Date {
        val date = seen.split("/")
        return Date(date[0].toInt(), date[1].toInt(), date[2].toInt())
    }
}
