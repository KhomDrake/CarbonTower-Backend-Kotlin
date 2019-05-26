package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.Unauthorized

class NotAAdministrator(private val idUser: String) : Unauthorized() {
    override var message: String = "Não é um administrador"
    override var messageLog: String = "Usuário $idUser não é um administrador em nenhum campeonato"
}