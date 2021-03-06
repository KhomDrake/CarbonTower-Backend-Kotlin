package com.carbontower.resources.database.exception

import com.carbontower.application.web.exceptions.Unauthorized

class NotAPlayer(private val idUser: String) : Unauthorized() {
    override var message: String = "Não tem permissão para acessar"
    override var messageLog: String = "Usuário $idUser não é um jogador"

}