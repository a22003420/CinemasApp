package com.example.cinemas_app.view



object History {
    //create list of history items
    val historyItems = mutableListOf<Filmes>(
        Filmes(
            "1",
            "Aves de Rapina",
            "Cinemas NOS",
            4,
            "2020",
            0,
            "Filme muito bom, recomendo"
        ),
        Filmes(
            "2",
            "Aves de Rapina",
            "Cinemas NOS",
            4,
            "2020",
            0,
            "Filme muito bom, recomendo"
        ),
    )

    fun getOperationById(uuid: String): Filmes? {
        return historyItems.find { it.id == uuid }
    }

}