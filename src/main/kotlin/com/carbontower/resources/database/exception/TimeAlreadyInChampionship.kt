package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class TimeAlreadyInChampionship(private val idTime: Int, private val idChampionship: Int) : NotAcceptable() {
    override var message: String = "Cadastro de Time em campeonato inválido"
    override var messageLog: String = "Time $idTime já participa do campeonato $idChampionship"
}