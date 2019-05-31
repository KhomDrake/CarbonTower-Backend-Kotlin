package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.NotAcceptable

class InviteAlreadyAnswer(private val idUser: String, private val idChampionship: Int) : NotAcceptable() {
    override var message: String = "Convite inválido"
    override var messageLog: String = "Convite para o usuário $idUser já foi respondido no campeonato $idChampionship"
}