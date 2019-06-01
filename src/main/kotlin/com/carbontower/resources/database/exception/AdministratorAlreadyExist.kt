package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.Unauthorized

class AdministratorAlreadyExist(private val idUser: String, private val idChampionship: Int) : Unauthorized() {
    override var message: String = "Já é um administrator"
    override var messageLog: String = "Usuário $idUser já é um administrador no campeonato $idChampionship"
}