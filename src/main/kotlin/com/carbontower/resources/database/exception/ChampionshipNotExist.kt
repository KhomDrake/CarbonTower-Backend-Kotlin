package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class ChampionshipNotExist(private val idChampionship: Int) : NotAcceptable() {
    override var message: String = "Dado(s) Inválidos"
    override var messageLog: String = "Campeonato de id $idChampionship não existe."
}