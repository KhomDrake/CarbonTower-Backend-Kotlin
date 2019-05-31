package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class MatchNotExist(private val idMatch: Int) : NotAcceptable() {
    override var message: String = "Dado de Partida inválido"
    override var messageLog: String = "Match $idMatch não existe."
}